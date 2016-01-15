package org.leafframework.util;
/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;



/**
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。
 * 
 */
public class RSAUtil {
	/**
	 * * 生成密钥对 *
	 * 
	 * @return KeyPair *
	 * @throws EncryptException
	 */
	public static KeyPair generateKeyPair() throws Exception {
		try {
			
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",new org.bouncycastle.jce.provider.BouncyCastleProvider());
			
			final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			saveKeyPair(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static KeyPair getKeyPair()throws Exception{
		FileInputStream fis = new FileInputStream("d:/RSAKey.txt");
		 ObjectInputStream oos = new ObjectInputStream(fis);
		 KeyPair kp= (KeyPair) oos.readObject();
		 oos.close();
		 fis.close();
		 return kp;
	}
	
	public static void saveKeyPair(KeyPair kp)throws Exception{
		
		 FileOutputStream fos = new FileOutputStream("d:/RSAKey.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         //生成密钥
         oos.writeObject(kp);
         oos.close();
         fos.close();
	}

	/**
	 * * 生成公钥 *
	 * 
	 * @param modulus *
	 * @param publicExponent *
	 * @return RSAPublicKey *
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus,
			byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
				modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * * 生成私钥 *
	 * 
	 * @param modulus *
	 * @param privateExponent *
	 * @return RSAPrivateKey *
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
			byte[] privateExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(
				modulus), new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * * 加密 *
	 * 
	 * @param key
	 *            加密的密钥 *
	 * @param data
	 *            待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
					: data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i
							* outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i
							* blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。

				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * * 解密 *
	 * 
	 * @param key
	 *            解密的密钥 *
	 * @param raw
	 *            已经加密的数据 *
	 * @return 解密后的明文 *
	 * @throws Exception
	 */
	public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
     public static byte charToByte(char c){
        return (byte) "0123456789ABCDEF".indexOf(c);
     }
     
     //加密改为实际密码
     public static String secretToOld(String secPass){
    	 String oldPass="";
    	 
    	  byte[] passwdbyte = hexStringToBytes(secPass);
  		
  		
  		String private16module="b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
          String private16empoent="528aad20d31880b672f3750fb7f99ba476add50eea9b7cc6a5d2dd19736da285cf2c488fcb1ef68083749b8a1fb5957ca16250fb8b1b64adca9707853c0c7bfa6618731061d464450163ecfa4c2a1580bf6dc03a39d9d45d31767fd036b4cddfe6c087ed6eca3e8128c669cffd3dfed3d08c6c58810b9cb5bcacfdfa8f65f381";
          RSAPrivateKey rsaPrivateKey;
		try {
			rsaPrivateKey = RSAUtil.generateRSAPrivateKey(new BigInteger(private16module, 16).toByteArray(), new BigInteger(private16empoent, 16).toByteArray());
			 byte[] real_passwd = RSAUtil.decrypt(rsaPrivateKey,passwdbyte);
	          
	  		 StringBuffer passwdsb = new StringBuffer();
	  		 
	  		 passwdsb.append(new String(real_passwd));
	  		 
	  		oldPass=URLDecoder.decode(passwdsb.reverse().toString(), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
         return oldPass ;
      }

	/**
	 * * *
	 * 
	 * @param args *
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		RSAPublicKey rsap = (RSAPublicKey) RSAUtil.generateKeyPair().getPublic();
//		String test = "hello world";
//		byte[] en_test = encrypt(getKeyPair().getPublic(),test.getBytes());
//		byte[] de_test = decrypt(getKeyPair().getPrivate(),en_test);
//		System.out.println("public16module=="+((RSAPublicKey)getKeyPair().getPublic()).getModulus().toString(16));
//		System.out.println("public16empoent=="+((RSAPublicKey)getKeyPair().getPublic()).getPublicExponent().toString(16));
//		System.out.println("private16module=="+((RSAPrivateKey)getKeyPair().getPrivate()).getModulus().toString(16));
//		System.out.println("private16empoent=="+((RSAPrivateKey)getKeyPair().getPrivate()).getPrivateExponent().toString(16));
//		System.out.println(new String(de_test));
		
//		String 加密后的串="79ee6b2b8d6c2db0666657d8e04c15f903254d67fc6b8d7e4f4f16fd7fbc663fd77334bbc6c1f6541eb4dc29ca02075b41bfd28fc0e58b72da60d676cfee34f88904b41adbf2bfe7dbbb1f1312c3f8fe5360e56f63b13a7967ffe770e62b6b685b8d5ee39a286dd86b7c5825702fb034504ee4dbe2ad971364c30e7d1fc38541";
//		byte[] 转成字节数组 = new BigInteger(加密后的串, 16).toByteArray();
//		StringBuffer sb = new StringBuffer();  
//        sb.append(new String(decrypt(getKeyPair().getPrivate(),转成字节数组)));  
//        System.out.println(sb.reverse().toString()); 
		
		/*String public16module="b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
		String public16empoent="10001";
		String private16module="b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
		String private16empoent="528aad20d31880b672f3750fb7f99ba476add50eea9b7cc6a5d2dd19736da285cf2c488fcb1ef68083749b8a1fb5957ca16250fb8b1b64adca9707853c0c7bfa6618731061d464450163ecfa4c2a1580bf6dc03a39d9d45d31767fd036b4cddfe6c087ed6eca3e8128c669cffd3dfed3d08c6c58810b9cb5bcacfdfa8f65f381";
		RSAPublicKey rsaPublicKey = RSAUtil.generateRSAPublicKey(new BigInteger(public16module, 16).toByteArray(), new BigInteger(public16empoent, 16).toByteArray());
		RSAPrivateKey rsaPrivateKey = RSAUtil.generateRSAPrivateKey(new BigInteger(private16module, 16).toByteArray(), new BigInteger(private16empoent, 16).toByteArray());
		String test = "1234567";
		byte[] en_test = encrypt(rsaPublicKey,test.getBytes());
		en_test="".getBytes();
		byte[] de_test = decrypt(rsaPrivateKey,en_test);	
		System.out.println(new String(de_test));*/
	    
	    String private16module="b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
        String private16empoent="528aad20d31880b672f3750fb7f99ba476add50eea9b7cc6a5d2dd19736da285cf2c488fcb1ef68083749b8a1fb5957ca16250fb8b1b64adca9707853c0c7bfa6618731061d464450163ecfa4c2a1580bf6dc03a39d9d45d31767fd036b4cddfe6c087ed6eca3e8128c669cffd3dfed3d08c6c58810b9cb5bcacfdfa8f65f381";
        RSAPrivateKey rsaPrivateKey = RSAUtil.generateRSAPrivateKey(new BigInteger(private16module, 16).toByteArray(), new BigInteger(private16empoent, 16).toByteArray());
	    String 加密后的串="34de8c1375c7f28800a5b3b425bb64bf2224ec815398b97b2d2ae86ef5836786629e01c30cc8b2c84874deb790a460adc31be8b783db2ba151f1cc16c59580c961bc31408ef87246b48a52ad03d1ba0abceffde435f9adc04e53238dc6ede20a89ce2e4fce56617717294d57d0d15f19238417253cc3b17f69b7562c0f6ab5d3";
	    /*这里:byte[] en_result = new BigInteger(result, 16).toByteArray();  
        有的时候你会发现数组长度129，第一个元素为0，这肯定是不正确的！
        解决办法:自己写一个16进制转字节数组的方法，很简单，在网上一搜一大堆！*/
        byte[] 转成字节数组 = hexStringToBytes(加密后的串);
            StringBuffer sb = new StringBuffer();  
        sb.append(new String(decrypt(rsaPrivateKey,转成字节数组)));  
        System.out.println(sb.reverse().toString()); 
	}
}
