package org.leafframework.constant;

public class Constants {
	
	/**
	 * 订单派单状态
	 * @author zhangyy
	 * 2015年12月15日
	 */
	public class AssignOrderState{
		
		/**
		 * 未派单
		 */
		public final static String NO_ASSIGN = "00";
		/**
		 * 派单成功
		 */
		public final static String ASSIGN_SUCCESS = "01";
		
		/**
		 * 派单失败
		 */
		public final static String ASSIGN_FAIL = "02";
		
	}

	/**
	 * 订单可执行的操作
	 * 
	 * @author wuyj
	 */
	public class OrderOperate {

		/**
		 * 订单取消
		 */
		public final static String ORDERCANCEL = "cancel";

		/**
		 * 订单支付成功
		 */
		public final static String ORDERPAYSUCCESS = "paysuccess";

		/**
		 * 订单支付失败
		 */
		public final static String ORDEROPERATEPAYFAIL = "payfail";

		/**
		 * 订单确认(货到付款订单)
		 */
		public final static String ORDERCONFIRM = "orderconfirm";

		/**
		 * 订单分配
		 */
		 public final static String ORDERASSIGN = "assign"; 

		/**
		 * 订单配送
		 */
		public final static String ORDEREXPRESS = "express";

		/**
		 * 订单签收
		 */
		public final static String ORDERSIGN = "sign";

	}
	
	/**
	 * 支付类型
	 * 
	 * @author wuyj
	 */
	public class PayType {
		
		/**
		 * 货到付款
		 */
		public final static String PAY_ON_DELIVERY = "pay_on_delivery";

		/**
		 * 0元支付
		 */
		public final static String ZEROPAY = "zeroPay";
		/**
		 * 支付宝在线支付
		 */
		public final static String ALIPAY = "al";
		/**
		 * 支付宝手机支付
		 */
		public final static String ALIAPPPAY = "ap";
		/**
		 * 支付宝手机网页支付
		 */
		public final static String ALIWAPPAY = "aw";
		
		/**
		 * 联行手机支付
		 */
		public final static String ECWAPPAY = "ew"; 
		/**
		 * 联行在线支付
		 */
		public final static String ECWA_ONLINE_PAY = "el"; 
		
		/**
		 * 易宝网页支付
		 */
		public final static String YEE_ONLINE_PAY = "yl"; 
		
		/**
		 * 银联支付
		 */
		public final static String UNION_PAY = "union";
		
		/**
		 * 微信支付
		 */
		public final static String WE_CHAT_PAY = "we_chat";
		
		
		//////////////////////////////////////////////////////////
		
		/**
		 * 易宝支付
		 */
		public final static String YEE_PAY_TYPE = "yeePay"; 
		
		/**
		 * 支付宝支付
		 */
		public final static String ALIPAYTYPE = "aliPay";
		
		/**
		 * 联行支付
		 */
		public final static String EW_PAY_TYPE = "ewPay"; 
		
		////////////////////////////////////////////////////////
		/**
		 * 在线支付
		 */
		public final static String ONLINEPAYTYPE = "onlinepay";
		
		/**
		 * 移动支付
		 */
		public final static String MOBILEPAYTYPE = "mobilepay";
		
	}
	
	/**
	 * 请求客户端类型
	 * 
	 * @author wuyj
	 */
	public class ReqClientType{
		/**
		 * PC端
		 */
		public final static String PCCLIENT = "web";
		
		/**
		 * 手机APP：android
		 */
		public final static String ANDROIDPHONECLIENT = "android";
		
		/**
		 * 手机APP：IOS
		 */
		public final static String IOSPHONECLIENT = "ios";
		
		/**
		 * 手机：微信
		 */
		public final static String WECHATPHONECLIENT = "wechat";
		
		/**
		 * 手机：网页
		 */
		public final static String PHONEWEBCLIENT = "wap";
	}

	/**
	 * 支付状态
	 * @author zhangyy
	 * 2015年5月29日
	 */
	public class PayState {

		/**
		 * 待支付
		 */
		public final static String PAYPREPARE = "PI00";

		/**
		 * 支付成功
		 */
		public final static String PAYFINISH = "PI01";

		/**
		 * 已退费
		 */
		public final static String PAYRETURN = "PI02";
		
		/**
		 * 退费中
		 */
		public final static String PAYRETURNING = "PI03";

	}
	
	/**
	 * 订单状态
	 * @author zhangyy
	 * 2015年5月26日
	 */
	public class OrderState {
		
		/**
		 * 待支付 OM00
		 */
		public final static String WAIT_PAY = "OM00";
		/**
		 * 待审核 OM01
		 */
		public final static String WAIT_APPROVE = "OM01";
		
		/**
		 * 审核不通过 OM02
		 */
		public final static String APPROVE_DISAGREE = "OM02";

		/**
		 * 待分配/待领取 OM03
		 */
		public final static String WAIT_DISPENSE = "OM03";

		/**
		 * 待开户 OM04
		 */
		public final static String WAIT_OPEN_ACCOUNT = "OM04";
		
		/**
		 * 开户失败 OM05
		 */
		public final static String OPEN_ACCOUNT_FAIL = "OM05";

		/**
		 * 待配送 OM06
		 */
		public final static String WAIT_DEVIDE = "OM06";

		/**
		 * 待签收 OM07
		 */
		public final static String WAIT_SIGN = "OM07";

		/**
		 * 已签收 OM08
		 */
		public final static String SIGN_SUCCESS = "OM08";

		/**
		 * 拒绝签收 OM09
		 */
		public final static String SIGN_FAIL = "OM09";
		
		/**
		 * 已取消 OM10
		 */
		public final static String CANCEL_ORDER = "OM10";
		/**
		 * 已失效 OM11
		 */
		public final static String INVALID_ORDER = "OM11";
		/**
		 * 已退单 OM12
		 */
		public final static String REFUND_ORDER = "OM12";
		/**
		 * 退回能人/自然人 OM13
		 */
		public final static String RETURN_CUSTOMER = "OM13";
		
		/**
		 * 待自提(配送方式为自提时) OM14
		 */
		public final static String WAIT_SELF_GET = "OM14";
		
		/**
		 * 归档成功 OM15
		 */
		public final static String ARCHIVE_SUCCESS = "OM15";
		
		/**
		 * 归档失败 OM16
		 */
		public final static String ARCHIVE_FAIL = "OM16";
	}

	/**
	 * 订单类型
	 * @author zhangyy
	 * 2015年5月26日
	 */
	public class OrderType {
		/**
		 * 普通订单
		 */
		public final static String ORDERTYPEGENERAL = "OT00";
		
		/**
		 * 推广订单
		 */
		public final static String ORDERTYPEPROMOTE = "OT01";
		
		/**
		 * 预装申请订单
		 */
		public final static String ORDERTYPEAPPLY = "OT02";
	}
	
	/**
	 * 回访结果
	 * @author zhangyy
	 * 2015年8月24日
	 */
	public class CallResult{
		/**
		 * 未回访
		 */
		public final static String NO_CALL = "CR00";
		/**
		 * 回访成功
		 */
		public final static String CALL_SUCCESS = "CR01";
		/**
		 * 回访失败
		 */
		public final static String CALL_FAIL = "CR02";
	}
	
	/**
	 * 订单礼品状态
	 * 
	 * @author wuyj
	 *
	 */
	public class OrderGiftType {
		/**
		 * 订单礼品生成
		 */
		public final static String ORDERGIFTGENERATE = "OG00";
		
		/**
		 * 订单礼品发放
		 */
		public final static String ORDERGIFTDISTRIBUTE = "OG01";
	}
	
	/**
	 * 订单物流状态
	 * 
	 * @author wuyj
	 *
	 */
	public class OrderExpressState {
		/**
		 * 待发货
		 */
		public final static String ORDEREXPRESSGENERATE = "OL00";
		
		/**
		 * 已发货
		 */
		public final static String ORDERREXPRESSFINISH = "OL01";
		
		/**
		 * 已签收
		 */
		public final static String ORDERREXPRESSSIGN = "OL02";
		/**
		 * 拒绝签收
		 */
		public final static String ORDERREXPRESSREFUSESIGN = "OL03";
	}
	
	/**
	 * 订单券卡状态
	 * 
	 * @author wuyj
	 *
	 */
	public class OrderCouponState {
		/**
		 * 订单券卡生成
		 */
		public final static String ORDERCOUPONGENERATE = "OC00";
		
		/**
		 * 订单券卡使用
		 */
		public final static String ORDERCOUPONUSED = "OC01";
		
		/**
		 * 订单券卡取消使用
		 */
		public final static String ORDERCOUPONCANCELUSED = "OC02";
	}
	
	/**
	 * 换货订单状态
	 * 
	 * @author wuyj
	 *
	 */
	public class ChangeOrderState {
		/**
		 * 换货订单生成
		 */
		public final static String CHANGEORDERGENERATE = "OE00";
		
		/**
		 * 审核通过
		 */
		public final static String CHANGEORDERREVIEWPASS = "OE01";
		
		/**
		 * 审核不通过
		 */
		public final static String CHANGEORDERREVIEWNOTPASS = "OE02";
		
		/**
		 * 已发货
		 */
		public final static String CHANGEORDEREXPRESS = "OE03";
		
	}
	
	/**
	 * 购物车状态
	 * 
	 * @author wanghao
	 *
	 */
	public class CartState {
		/**
		 * 正常待结算状态
		 */
		public final static String CARTWAITPAY = "CA00";

		/**
		 * 用户删除
		 */
		public final static String CARTUSERDELETE = "CA01";

		/**
		 * 已生成订单
		 */
		public final static String CARTTOORDER = "CA02";

	}

	/**
	 * 商品图片类型
	 * 
	 * @author wanghao
	 *
	 */
	public class GoodsPicType {
		/**
		 * 购物车摘要
		 */
		public final static String GOODSPICTYPE_CARTABS = "GP00";

		/**
		 * 商品列表
		 */
		public final static String GOODSPICTYPE_CARTLIST = "GP01";
		
		/**
		 * 商品详情
		 */
		public final static String GOODSPICTYPE_GOODSDETAIL = "GP02";

	}

	/**
	 * 活动图片类型
	 * 
	 * @author wanghao
	 *
	 */
	public class PromotionPicType {
		/**
		 * 购物车列表活动图片
		 */
		public final static String PROMOTIONPICTYPE_CARTLIST = "PP00";

	}

	/**
	 * 客户关注状态
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerFocusState {
		/**
		 * 已关注
		 */
		public final static String CUSTOMERFOCUSSTATE_FOCUS = "FC00";

		/**
		 * 取消关注
		 */
		public final static String CUSTOMERFOCUSSTATE_NOFOCUS = "FC01";
	}

	/**
	 * 关注类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerFocusType {
		/**
		 * 商品
		 */
		public final static String CUSTOMERFOCUSGOODS = "FT00";

		/**
		 * 活动
		 */
		public final static String CUSTOMERFOCUSPROMOTION = "FT01";
	}

	/**
	 * 商品状态
	 * 
	 * @author wanghao
	 *
	 */
	public class GoodsState {
		/**
		 * 禁售
		 */
		public final static String GOODSSTATE_NOSALE = "GD00";

		/**
		 * 在售
		 */
		public final static String GOODSSTATE_SALE = "GD01";
	}

	/**
	 * 活动状态
	 * 
	 * @author wanghao
	 *
	 */
	public class PromotionState {
		/**
		 * 正常进行
		 */
		public final static String PROMOTIONSTATE_DOING = "1";

	}

	/**
	 * 客户状态
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerState {
		/**
		 * 通过
		 */
		public final static String CUSTOMERSTATE_USING = "CU00";
		
		/**
		 * 审核中
		 */
		public final static String CUSTOMERSTATE_AUDITING = "CU01";
		
		/**
		 * 不通过
		 */
		public final static String CUSTOMERSTATE_DELETED = "CU02";
		
		/**
		 * 上传身份证
		 */
		public final static String CUSTOMERSTATE_IDCARD = "CU03";
		
		/**
		 * 冻结
		 */
		public final static String CUSTOMERSTATE_FROZEN = "CU04";
		
		/**
		 * 已删除
		 */
		public final static String CUSTOMERSTATE_DELETED_FOREVER = "CU05";
	
	}
	
	/**
	 * 黑名单状态
	 * 
	 * @author zhanggs
	 *
	 */
	public class BlackListState {
		/**
		 * 有效
		 */
		public final static String BLACKLISTSTATE_VALID = "有效";
		
		/**
		 * 无效
		 */
		public final static String BLACKLISTSTATE_INVALID = "已过期";
	
	}

	/**
	 * 收货地址状态
	 * 
	 * @author wanghao
	 *
	 */
	public class ExpressState {
		/**
		 * EX00：正常使用
		 */
		public final static String EXPRESSSTATE_USING = "EX00";

		/**
		 * EX01：弃用
		 */
		public final static String EXPRESSSTATE_DELETED = "EX01";

	}

	/**
	 * 发票信息状态
	 * 
	 * @author wuyj
	 *
	 */
	public class InvoiceState {
		/**
		 * 正常使用
		 */
		public final static String INVOICESTATE_USING = "IN00";

		/**
		 * 弃用
		 */
		public final static String INVOICESTATE_DELETED = "IN01";

	}
	
	/**
	 * 发票类型
	 * 
	 * @author wanghao
	 *
	 */
	public class InvoiceType {
		/**
		 * 普通发票
		 */
		public final static String INVOICETYPE_NORMAL = "IT00";

		/**
		 * 增值税发票
		 */
		public final static String INVOICETYPE_ADDEDTAX = "IT01";

	}
	
	/**
	 * 发票是否默认选中
	 * 
	 * @author wanghao
	 *
	 */
	public class InvoiceIsDefault {
		/**
		 * 默认选中
		 */
		public final static String INVOICE_DEFAULT = "1";

		/**
		 * 非默认选中
		 */
		public final static String INVOICE_UNDEFAULT = "0";

	}
	
	/**
	 * 是否默认地址
	 * 
	 * @author wanghao
	 *
	 */
	public class IsDefaultExpress {
		/**
		 * 默认地址
		 */
		public final static String ISDEFAULTEXPRESS_DEFAULT = "1";

		/**
		 * 非默认地址
		 */
		public final static String ISDEFAULTEXPRESS_NODEFAULT = "0";

	}

	/**
	 * 订阅类型
	 * 
	 * @author wanghao
	 *
	 */
	public class SubscribeType {
		/**
		 * 订阅订单生成类信息
		 */
		public final static String SUBSCRIBETYPE_ORDERGEN = "SU00";

		/**
		 * 订阅支付完成类信息
		 */
		public final static String SUBSCRIBETYPE_ORDERPAIED = "SU01";

		/**
		 * 订阅配送完成（已发货）类信息
		 */
		public final static String SUBSCRIBETYPE_ORDEREXPRESSED = "SU02";

		/**
		 * 订阅订单完成类信息
		 */
		public final static String SUBSCRIBETYPE_ORDERFINSH = "SU03";

		/**
		 * 订阅关注类信息
		 */
		public final static String SUBSCRIBETYPE_FOCUS = "SU04";
		
		/**
		 * 优惠代金劵
		 */
		public final static String MAILTYPE_COUPON  = "SU05";

	}

	/**
	 * 浏览类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerBrowserType {
		/**
		 * 商品
		 */
		public final static String CUSTOMERBROWSERGOODS = "BR00";

		/**
		 * 活动
		 */
		public final static String CUSTOMERBROWSERPROMOTION = "BR01";
	}

	/**
	 *客户类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerUserSource {
		/**
		 * CO00：网站申请
		 */
		public final static String CUSTOMERSOURCE_WEB = "CO00";
		
		/**
		 * CO01：手机客户端申请
		 */
		public final static String CUSTOMERSOURCE_CLIENT = "CO01";
		
		/**
		 * CO02：微信申请
		 */
		public final static String CUSTOMERSOURCE_WECHAT = "CO02";
		
		/**
		 * CO03：管理员创建
		 */
		public final static String CUSTOMERSOURCE_MANADD = "CO03";
	}

	/**
	 * 投诉状态
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerComplaintState {
		/**
		 * 未答复
		 */
		public final static String COMPLAINTNOREPLY = "OS00";

		/**
		 * 已答复
		 */
		public final static String COMPLAINTREPLIED = "OS01";

	}

	/**
	 * 劵卡类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CouponType {
		/**
		 * CP00:代金券
		 */
		public final static String COUPONCASH = "CP00";

		/**
		 * CP01:红包
		 */
		public final static String COUPONREDPACKET = "CP01";

	}
	
	/**
	 * 客户劵卡类型
	 * @author wanghao
	 *
	 */
	public class CustomerCouponType {
		/**
		 * UT00:劵
		 */
		public final static String COUPONCOUPON = "UT00";

		/**
		 * UT01:卡
		 */
		public final static String COUPONCARD = "UT01";

	}

	/**
	 * 客户劵卡使用状态
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerCouponUseType {
		/**
		 * US00:未使用
		 */
		public final static String COUPONCASH = "US00";

		/**
		 * US01:已使用
		 */
		public final static String COUPONREDPACKET = "US01";

	}

	/**
	 * 劵卡是否可交换
	 * 
	 * @author wanghao
	 *
	 */
	public class CouponIsExchange {
		/**
		 * 0:不可交换
		 */
		public final static String COUPONUNEXCHANGE = "0";

		/**
		 * 1:可交换
		 */
		public final static String COUPONCANEXCHANGE = "1";

	}

	/**
	 * 是否禁用
	 * 
	 * @author wanghao
	 *
	 */
	public class CouponIsForbidden {
		/**
		 * 0:可用
		 */
		public final static String COUPONUNFORBIDDEN = "0";

		/**
		 * 1:禁用
		 */
		public final static String COUPONFORBIDDEN = "1";

	}
	
	/**
	 * 劵卡来源
	 * 
	 * @author wanghao
	 *
	 */
	public class CouponSource {
		/**
		 * NS00:系统推送
		 */
		public final static String COUPONUNUNCLAIM = "NS00";

		/**
		 * NS01:个人自领
		 */
		public final static String COUPONCLAIMED = "NS01";

	}

	/**
	 * 积分操作类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerScoreOptType {
		/**
		 * SC00:消费 
		 */
		public final static String SCOREOPTCONSUME = "SC00";

		/**
		 * SC01:奖励
		 */
		public final static String SCOREOPTREWARD = "SC01";
		
		/**
		 * SC02:兑换
		 */
		public final static String SCOREOPTEXCHANGE = "SC02";
		
		/**
		 * SC03:初始导入
		 */
		public final static String SCOREOPTEXPORT = "SC03";
		
		/**
		 * SC04:佣金生成 --(作废) 佣金生成同初始导入合并
		 */
		public final static String SCOREOPTGENBYCOMMISSON = "SC04";

	}

	/**
	 * 账户类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerAccountType {
		/**
		 * AT00:银联账户
		 */
		public final static String CUSTOMERACCOUNTUNION = "AT00";

		/**
		 * AT01:支付宝账户
		 */
		public final static String CUSTOMERACCOUNTALIPAY = "AT01";

	}

	/**
	 * 客户类型
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerType {
		/**
		 * CT00:自然人
		 */
		public final static String NOMALCUSTOMERTYPE = "CT00";
		
		/**
		 * CT01:能人
		 */
		public final static String AICUSTOMERTYPE = "CT01";
		
		/**
		 * CT02:维系中心
		 */
		public final static String WXCUSTOMERTYPE = "CT02";
		
		/**
		 * CT03:自然用户
		 */
		public final static String ZRCUSTOMERTYPE = "CT03";
		
		/**
		 * CT04:10016
		 */
		public final static String QDCUSTOMERTYPE = "CT04";

		/**
		 * CT05:客服呼叫中心
		 */
		public final static String KFCUSTOMERTYPE = "CT05";
		
	}

	/**
	 * 客户级别
	 * 
	 * @author wanghao
	 *
	 */
	public class CustomerLevel {
		/**
		 * CL00:酒虫
		 */
		public final static String CUSTOMERLEVEL1 = "CL00";
		/**
		 * CL01:酒鬼
		 */
		public final static String CUSTOMERLEVEL2 = "CL01";
		
		/**
		 * CL02:酒仙
		 */
		public final static String CUSTOMERLEVEL3 = "CL02";
		
		/**
		 * CL03:普通经销商
		 */
		public final static String CUSTOMERLEVEL4 = "CL03";
		
		/**
		 * CL04:金牌经销商
		 */
		public final static String CUSTOMERLEVEL5 = "CL04";

	}
	
	/**
	 * 账单类型
	 * 
	 * @author wuyj
	 *
	 */
	public class AccountType {
		/**
		 * AB00:商城获得（充值账单） 
		 */
		public final static String ACCOUNTDEPOSITTYPE = "AB00";
		
		/**
		 * AB01:商城支付（消费账单）
		 */
		public final static String ACCOUNTCONSUMETYPE = "AB01";
		
		/**
		 * AB02:个人提现（提现账单）
		 */
		public final static String ACCOUNTWITHDRAWTYPE = "AB02";
		
		/**
		 * AB03:系统冲抵
		 */
		public final static String ACCOUNTMISTAKE = "AB03";
		
		/**
		 * AB03:系统返还
		 */
		public final static String SYSTEMRETURN = "AB04";
		
	}
	
	/**
	 * 账单状态
	 * 
	 * @author wuyj
	 *
	 */
	public class AccountOrderType {
		/**
		 * 0:未结账
		 */
		public final static String ACCOUNTORDERUNCHECKOUT = "0";
		
		/**
		 * 1:已结账
		 */
		public final static String ACCOUNTORDERCHECKOUT = "1";
		
		/**
		 * 3:账单作废
		 */
		public final static String ACCOUNTORDERCANCEL = "3";
		
	}
	
	/**
	 * 计算引擎规则调用
	 * @author wanghao
	 *
	 */
	public class AceRoleName{
		/**
		 * 	购物车首页规则
		 */
		public final static String ACEROLENAME_CART_USING = "cartgoodsprice";
		/**
		 * 	购物车摘要
		 */
		public final static String ACEROLENAME_CART_ABSTRACT = "zaiyao";
		/**
		 *    获取商品价格
		 */
		public final static String ACEROLENAME_GOODS_PRICE = "goodsallprice";
		/**
		 * 商品详情价格
		 */
		public final static String ACEROLENAME_DETAIL_PRICE = "goodsdetailprice";

	}
	
	/**
	 * 商品类型
	 * @author wuyj
	 *
	 */
	public class goodsType{
		/**
		 * 	4G全国套餐
		 */
		public final static String GOODSTYPEPACKAGE = "GT00";
		/**
		 * 	4G校园套餐
		 */
		public final static String GOODSTYPECAMPUSPACKAGE = "GT03";
		/**
		 * 	4G本地套餐
		 */
		public final static String GOODSTYPELOCALPACKAGE = "GT04";
		
		/**
		 * 	单宽带
		 */
		public final static String GOODSTYPEBROADBAND = "GT05";
		
		/**
		 * 	智慧沃家
		 */
		public final static String GOODSTYPEWOFAMILY = "GT06";
		
		/**
		 * 3G存费送费
		 */
		public final static String GOODSTYPE3GCHARGEFEE = "GT07";
		
		/**
		 * 上网卡
		 */
		public final static String GOODSTYPEWEBCARD = "GT08";
		
		/**
		 * 青岛38元沃派
		 */
		public final static String GOODSTYPEQDWOTAOCAN = "GT09";
		
		/**
		 * 4g存费送费（全国）
		 */
		public final static String GOODSTYPE4GCHARGEFEE = "GT10";
		
		/**
		 * 4g存费送费（校园）
		 */
		public final static String GOODSTYPE4GCHARGEFEE_CAMPUS = "GT11";
		
		/**
		 * 4g存费送费（本地）
		 */
		public final static String GOODSTYPE4GCHARGEFEE_LOCAL = "GT12";
		
		/**
		 * 4G流量王
		 */
		public final static String GOODSTYPE4GFLOWKING = "GT13";
		
		/**
		 * 4G9分卡
		 */
		public final static String GOODSTYPE4GNINECARD = "GT14";
		
		/**
		 * 4G主副卡
		 */
		public final static String GOODSTYPE4GZFKCARD = "GT15";
		
		/**
		 * 4G合约惠机
		 */
		public final static String GOODSCONTRACTSMACHINE = "GT17";
		
		/**
		 * 	融合类商品
		 */
		public final static String GOODSTYPEMIX  = "GT01";
		
		/**
		 * 裸机
		 */
		public final static String GOODSTYPEUNLOCKEDPHONE = "GT02";
		
	}
	
	/**
	 * 返利类型
	 * @author wuyj
	 *
	 */
	public class custRebateType{
		/**
		 * 	单商品返利
		 */
		public final static String SINGLEGOODSREBATE = "ET00";
		
		/**
		 * 	订单返利
		 */
		public final static String ORDERFEEREBATE  = "ET01";
		
	}
	
	/**
	 * 经验值详情状态
	 * @author wuyj
	 *
	 */
	public class custExperienceStateType{
		/**
		 * 	经验值已充正
		 */
		public final static String CUSTEXPALREADEADD = "ES00";
		
		/**
		 * 	经验值未充正
		 */
		public final static String CUSTEXPNOTADD  = "ES01";
		
		/**
		 * 	经验值回退
		 */
		public final static String CUSTEXPREBACK  = "ES02";
		
		/**
		 * 	经验值作废
		 */
		public final static String CUSTEXPNULLIFY  = "ES03";
		
	}
	
	/**
	 * 活动构成类型
	 * @author wanghao
	 *
	 */
	public class promotionCombType
	{
		/**
		 * 	商品类
		 */
		public final static String PROCOMGOODS  = "0000";
		
		/**
		 * 	赠品类
		 */
		public final static String PROCOMGIFTS  = "0001";
	}
	
	/**
	 * 邮件推送状态
	 * @author wanghao
	 *
	 */
	public class mailState
	{
		/**
		 * 	未发送
		 */
		public final static String MAILSTATE_UNSEND  = "00";
		
		/**
		 * 	已发送
		 */
		public final static String MAILSTATE_SENDED  = "01";

	}
	
	/**
	 * 活动类型
	 * @author wanghao
	 *
	 */
	public class promotionType
	{
		/**
		 * 	PT00:买赠
		 */
		public final static String PROMOTIONTYPE_GIVE  = "PT00";
		
		/**
		 * 	PT01:抢购
		 */
		public final static String PROMOTIONTYPE_SNAP  = "PT01";
		
		/**
		 * 	PT02:团购
		 */
		public final static String PROMOTIONTYPE_GROUP  = "PT02";

	}
	
	/**
	 * 配送方式
	 * @author zhangyy
	 * 2015年5月21日
	 */
	public class DeliverType{
		/**
		 * 顺丰
		 */
		public final static String SF  = "DT00";
		/**
		 * EMS
		 */
		public final static String EMS  = "DT01";
		/**
		 * 自提
		 */
		public final static String SELF_GET  = "DT02";
		/**
		 * 无需配送
		 */
		public final static String NO_DELIVER  = "DT03";
	}
	
	/**
	 * 配送时间
	 * @author zhangyy
	 * 2015年5月21日
	 */
	public class DeliverTime{
		/**
		 * 不限配送时间
		 */
		public final static String NO_LIMIT  = "DT00";
		/**
		 * 只工作日配送
		 */
		public final static String WORK_DAY  = "DT01";
		/**
		 * 只周末或节假日配送
		 */
		public final static String WEEKEND  = "DT02";
	}
	
	/**
	 * 属性类型
	 * @author wanghao
	 *
	 */
	public class AttributeValueType
	{
		/**
		 * 	number：数字类型
		 */
		public final static String VALUETYPE_NUMBER  = "number";
		
		/**
		 * string：字符类型
		 */
		public final static String VALUETYPE_STRING  = "string";
		
		/**
		 * array：数组类型
		 */
		public final static String VALUETYPE_ARRAY  = "array";
		
		/**
		 * date：日期类型
		 */
		public final static String VALUETYPE_DATE  = "date";
	}
	
	/**
	 * 渠道类型
	 * @author wanghao
	 *
	 */
	public class ChannelType
	{
		/**
		 * CH00：能人
		 */
		public final static String CHANNEL_ABLEPERSON  = "CH00";
		
	}
	
	/**
	 * 订单状态变更反馈类型
	 * @author zhangyy
	 * 2015年5月26日
	 */
	public class OrderRebackType{
		/**
		 * 身份证信息无效或在黑名单中
		 */
		public final static String IDCARD_INVALID = "RT00";
		/**
		 * 其他无效信息
		 */
		public final static String OTHER_INVALID = "RT01";
	}
	
	/**
	 * 购物车延时标识
	 * @author wanghao
	 *
	 */
	public class DelayFlag
	{
		/**
		 * 0：未延时
		 */
		public final static String NONE_DELAY  = "0";
		
		/**
		 * 1：延时
		 */
		public final static String WAS_DELAY  = "1";
		
	}
	
	/**
	 * 退货状态
	 * @author zhangyy
	 * 2015年6月4日
	 */
	public class ReturnOrderState{
		/**
		 * 待审核
		 */
		public static final String WAIT_APPROVE= "RS00";
		/**
		 * 审核通过
		 */
		public static final String AGREE= "RS01";
		/**
		 * 审核不通过
		 */
		public static final String REFUSE= "RS02";
		/**
		 * 退货成功
		 */
		public static final String RETURN_SUCCESS= "RS03";
	}
	
	/**
	 * 订单分配状态
	 * @author zhangyy
	 * 2015年6月8日
	 */
	public class DispenseState{
		
		/**
		 * 待开户
		 */
		public static final String WAIT_OPEN_ACCOUNT = "DS00";
		
		/**
		 * 开户成功
		 */
		public static final String OPEN_ACCOUNT_SUCCESS = "DS01";
		
		/**
		 * 开户失败
		 */
		public static final String OPEN_ACCOUNT_FAIL = "DS02";
	}
	
	/**
	 * 保证金状态
	 * @author zhangjb
	 * 2015年6月17日
	 */
	public class DepositState{
		
		/**
		 * 未缴纳
		 */
		public static final String DEPOSIT_UNPAID = "PS00";
		
		/**
		 * 已缴纳
		 */
		public static final String DEPOSIT_PAID = "PS01";
		
		/**
		 * 已退还
		 */
		public static final String DEPOSIT_REFUND = "PS02";

	}
	
	/**
	 * 网盟系统所属省
	 * @author wanghao
	 * 2015年6月18日
	 */
	public class AblePersonProvince
	{
		/**
		 * 网盟PLUS所属省：山东省
		 */
		public static final String NETCYCLEPROVINCE = "山东省";
	}
	
	/**
	 * 配送标志位
	 * @author wanghao
	 *
	 */
	public class CustomerExpressFlag
	{
		/**
		 * 0：仅需自提
		 */
		public static final String CUSTOMERPICKUP="0"; 
		/**
		 * 1：允许配送
		 */
		public static final String SYSTEMDELIVERY = "1";
	}
	
	/**
	 * 保证金账单类型
	 * @author shh
	 *
	 */
	public class DepositbillOperatingType
	{
		/**
		 * TT00:缴纳;
		 */
		public static final String DEPOSIT_PAY = "TT00";
		/**
		 * TT01：扣款；
		 */
		public static final String DEPOSIT_DEDUCT = "TT01";
		/**
		 * TT02: 退还;
		 */
		public static final String DEPOSIT_RETURN = "TT02";
	}
	
	/**
	 * 保证金账单状态
	 * @author shh
	 *
	 */
	public class DepositbillState
	{
		/**
		 * AS00:未结账;
		 */
		public static final String DEPOSIT_NOPAY = "AS00";
		/**
		 * AS01:已结账；
		 */
		public static final String DEPOSIT_PAY = "AS01";
		/**
		 * AS03:已退款；
		 */
		public static final String REBACK_PAY = "AS03";
		/**
		 * AS02:审核不通过；
		 */
		public static final String DEPOSIT_CHECKFAIL = "AS02";
	}
	
	/**
	 * 号码状态
	 * @author wanghao
	 *
	 */
	public class NumberState
	{
		/**
		 * 00:入库
		 */
		public static final String PUTINSTORAGE = "00";
		/**
		 * 01:上架
		 */
		public static final String CANSALE = "01";
		/**
		 * 02:预占   用户在前台点击号码时变为此状态
		 */
		public static final String BOOKNUMBER = "02";
		/**
		 * 03：预定 用户预约成功或用户在支付的前一步为此状态
		 */
		public static final String RESERVATIONNUMBER = "03";
		/**
		 * 04：付费预订
		 */
		public static final String PAIEDRESERVATION = "04";
		/**
		 * 05:   开户
		 */
		public static final String OPENBUSSINESS = "05";
		/**
		 * 06：下架
		 */
		public static final String CANNOTSALE = "06";
		/**
		 * 07：出库
		 */
		public static final String PUTOUTSTORAGE = "07";
		/**
		 * 08:代表活动的号码
		 */
		public static final String PROMOTIONNUMBER = "08";
	}
	
	/**
	 * 佣金类型
	 * @author zhangjb
	 *
	 */
	public class BillType{
		
		/**
		 * 固定佣金
		 */
		public static final String FIXED_BILL = "BT00";
		
		/**
		 * 分成佣金
		 */
		public static final String DIVIDE_BILL = "BT02";
		
		/**
		 * 奖罚佣金
		 */
		public static final String REWARD_BILL = "BT01";
		
		/**
		 * 活动佣金
		 *//*
		public static final String ACTIVITY_BILL = "BT01";
		
		*//**
		 * 补发佣金
		 *//*
		public static final String REISSUE_BILL = "BT03";
		
		*//**
		 * 奖励佣金
		 *//*
		public static final String REWARD_BILL = "BT04";
		
		*//**
		 * 扣罚佣金
		 *//*
		public static final String LOSES_BILL = "BT05";*/
		
	}
	
	/**
	 * 佣金状态
	 * @author zhangjb
	 *
	 */
	public class BillState{
		
		/**
		 * 初始导入
		 */
		public static final String INIT_IMP = "BS00";

		/**
		 * 地市已确认
		 */
		public static final String AREA_CONFIM_BILL = "BS07";
		
		/**
		 * 佣金待发
		 */
		public static final String SNAP_BILL = "BS01";
		
		/**
		 * 佣金已发
		 */
		public static final String HAD_BILL = "BS02";
		
		/**
		 * 发放失败
		 */
		public static final String FAIL_BILL = "BS03";
		
		/**
		 * 佣金二次发放
		 */
		public static final String SNAP_TWO_BILL = "BS04";
		
		/**
		 * 佣金匹配失败
		 */
		public static final String BILL_MATCH_ERROR = "BS05";
		
		/**
		 * 佣金匹配成功
		 */
		public static final String BILL_MATCH_SUCCESS = "BS06";
	}
	
	/**
	 * 积分兑换规则 兑换状态
	 * @author zhangjb
	 *
	 */
	public class ExchangeState{
		
		/**
		 * 已兑换
		 */
		public static final String YES = "CR00";
		
		/**
		 * 未兑换
		 */
		public static final String NO = "CR01";
	}
	
	/**
	 * 接入方式
	 * @author wanghao
	 *
	 */
	public  class AccessMode
	{
		/**
		 * ADSL编码
		 */
		public static final String ACCESSMODE_ADSL_CODE = "0";
		/**
		 * LAN编码
		 */
		public static final String ACCESSMODE_LAN_CODE = "1";
		/**
		 * FTTH编码
		 */
		public static final String ACCESSMODE_FTTH_CODE = "2";
		/**
		 * ADSL
		 */
		public static final String ACCESSMODE_ADSL = "ADSL";
		/**
		 * LAN
		 */
		public static final String ACCESSMODE_LAN = "LAN";
		/**
		 * FTTH
		 */
		public static final String ACCESSMODE_FTTH = "FTTH";
	}
	
	/**
	 * 宽带速率
	 * @author wanghao
	 *
	 */
	public  class BroadSpeed
	{
		/**
		 * 4M编码
		 */
		public static final String BROADSPEED_4M_CODE = "0";
		/**
		 * 10M编码
		 */
		public static final String BROADSPEED_10M_CODE = "1";
		/**
		 * 20M编码
		 */
		public static final String BROADSPEED_20M_CODE = "2";
		/**
		 * 2M编码
		 */
		public static final String BROADSPEED_2M_CODE = "3";
		/**
		 * 50M编码
		 */
		public static final String BROADSPEED_50M_CODE = "4";
		/**
		 * 4M
		 */
		public static final String BROADSPEED_4M = "4M";
		/**
		 * 10M
		 */
		public static final String BROADSPEED_10M = "10M";
		/**
		 * 20M
		 */
		public static final String BROADSPEED_20M = "20M";
		/**
		 * 2M
		 */
		public static final String BROADSPEED_2M = "2M";
		/**
		 * 50M
		 */
		public static final String BROADSPEED_50M = "50M";
	}
	
	/**
	 * 需求类型
	 * @author wanghao7
	 *
	 */
	public class DemandType{
		
		/**
		 * MT00:宽带预装
		 */
		public static final String BROADTYPE = "MT00";
		
		/**
		 * MT01:智慧沃家预装
		 */
		public static final String SMARTHOMETYPE = "MT01";
	}
	
	/**
	 * 需求处理状态
	 * @author wanghao7
	 *
	 */
	public class DemandDealState{
		
		/**
		 * DM00：未处理
		 */
		public static final String WAITDEAL = "DM00";
		
		/**
		 * DM01：取消
		 */
		public static final String DEMANDCANCEL = "DM01";
		
		/**
		 *DM02：已处理（生成订单）手机端；PC端；wechat；第三方渠道；
		 */
		public static final String DEMANTOORDER = "DM02";
	}
	
	/**
	 * 来源需求
	 * @author wanghao7
	 *
	 */
	public class DemandSource{
		
		/**
		 * MS00：手机端
		 */
		public static final String DEMANDSOURCEPHONE = "MS00";
		
		/**
		 * MS01：PC端
		 */
		public static final String  DEMANDSOURCEPC = "MS01";
		
		/**
		 *MS02：wechat
		 */
		public static final String  DEMANDSOURCEWECHAT = "MS02";
		
		/**
		 *MS03：第三方渠道
		 */
		public static final String  DEMANDSOURCETHIRD = "MS03";
	}
	
	/**
	 * 保证金常量
	 * @author shh
	 *
	 */
	public class DepositConstants{
		
		public static final String DEPOSITSTATETYPE = "DEPOSIT";
		
		public static final String DEPOSITSTATENAME = "DEPOSITLIMIT";
	}
	
	/**
	 * 能人空间状态
	 * @author wanghao
	 *
	 */
	public class SpaceState
	{
		/**
		 * ST00：未开通
		 */
		public static final String SPACE_NONEOPEN = "ST00";
		
		/**
		 * ST01：已开通
		 */
		public static final String SPACE_OPENED = "ST01";
		
		/**
		 * ST02：已冻结
		 */
		public static final String SPACE_FREEZON = "ST02";
		
		/**
		 * ST03：已关闭
		 */
		public static final String SPACE_CLOSED = "ST03";
	}
	
	/**
	 * 佣金账期生成状态
	 * @author zhangjb
	 *
	 */
	public class GenerateState{
		/**
		 * 已生成
		 */
		public static final String GENERATED = "GENERATED";
		/**
		 * 未生成
		 */
		public static final String NOT_GENERATED = "NOT_GENERATED";
		/**
		 * 生成失败
		 */
		public static final String FAIL_GENERATED = "FAIL_GENERATED";
	}
	
	/**
	 * 地市佣金导入确认状态
	 * @author zhangjb
	 *
	 */
	public class confirmState{
		/**
		 * 已确认
		 */
		public static final String YES_CONFIRM = "YES_CONFIRM";
		/**
		 * 未确认
		 */
		public static final String NO_CONFIRM = "NO_CONFIRM";
		
	}
	/**
	 *AES加密密钥 
	 */
	public class AESPassKey
	{
		/**
		 * 加密密钥
		 */
		public static final String AESENCODEPWD_KEY = "kqhkiG9w0BAQEFAASCAl8wgg";
	}
	
	/**
	 * 佣金导入状态
	 */
	public class billImportState{
		
		/**
		 * 未导入
		 */
		public static final String BILL_NOT_IMPORT = "BI00";
		
		/**
		 * 导入中
		 */
		public static final String BILL_ING_IMPORT = "BI02";
		
		/**
		 * 已导入
		 */
		public static final String BILL_YES_IMPORT = "BI01";
		
		/**
		 * 已匹配处理
		 */
		public static final String BILL_YES_MATCH = "BI03";
	}
	
	/**
	 * 需求汇集处理状态
	 * @author wanghao7
	 *
	 */
	public class RequireDealState{
		
		/**
		 * RM00：未处理
		 */
		public static final String WAITDEAL = "DM00";
		
		/**
		 * RM01：已处理
		 */
		public static final String DEMANDCANCEL = "DM01";
	}
	
}
