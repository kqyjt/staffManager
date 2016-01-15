package org.leafframework.util;

public final class RETURN {
	
	private String errorCode = "00000";
	private String errorMsg = "操作执行成功";
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public RETURN(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public static RETURN SUCCESS = new RETURN("00000", "服务执行成功！");
	public static RETURN THROW_ERROR = new RETURN("99999", "服务程序执行异常,请联系管理员");
	public static RETURN PWD_ERROR = new RETURN("10001", "用户密码错误！");
	public static RETURN NO_USER = new RETURN("10002", "登录验证，无此用户");
	public static RETURN DB_ERROR = new RETURN("10003", "数据库操作异常，请联系管理员！");
	public static RETURN CHK_ERROR = new RETURN("10004", "登录验证，验证码不正确");
	public static RETURN SES_TIME_OUT = new RETURN("10005", "请先登录");
	public static RETURN REQ_PARAM_ERROR = new RETURN("10006", "至少需要一个查询条件，请您确认查询条件！");
	public static RETURN REQ_TRADE_ERROR = new RETURN("10007", "业务逻辑错误，请点击明细核查");
	public static RETURN REQ_ACTION_ERROR = new RETURN("10008", "Action对应的业务逻辑方法设置不正确");
	public static RETURN PAY_RESULT_FAIL = new RETURN("10009", "支付失败，请检查账户是否扣款！");
	
	public static RETURN PAY_INFO_NOTEXIST = new RETURN("10010", "未查询到相应的支付订单！");
	public static RETURN REQUIRED_PARAMS_IS_NULL = new RETURN("10011", "必传的请求参数不能为空！");
	public static RETURN ROUTE_PUSH_APPLY_FAIL = new RETURN("10012", "路由推送申请失败！");
	public static RETURN NTACCOUNT_ERROR = new RETURN("10013", "企业专场nt账号验证失败！");
	public static RETURN ADD_CARTITEM_FAILED = new RETURN("10014", "加入购物车失败");
	public static RETURN CHK_EXITSGOODS = new RETURN("10015", "该商品不存在！");
	public static RETURN CHK_GOODSISONSALE = new RETURN("10016", "商品已经下架！");
	public static RETURN ORDER_STATE_CHANGE_FAIL = new RETURN("10017", "订单状态变更失败！");
	public static RETURN NO_GOODS_FOUND_IN_ORDER = new RETURN("10018", "订单中未包含任何商品信息！");
	public static RETURN ORDER_EXPRESS_FEE_CHECK_FAIL = new RETURN("10019", "订单物流费用校验不通过！");
	public static RETURN INVOKE_ACE_FAILED = new RETURN("10020", "价格获取失败");
	public static RETURN GETATTRIBUTE_ERR = new RETURN("10021", "获取商品属性值失败");
	public static RETURN CARTGOODS_DELAYBOOK_ERR = new RETURN("10022", "购物车商品只能延时一次");
	public static RETURN NONE_CARTGOODS_ERR = new RETURN("10023", "延时失败，购物车中没有此商品");
	public static RETURN ORDER_FEE_CHECK_FAIL = new RETURN("10024", "订单费用校验不通过！");
	public static RETURN REPEAT_CANCEL_ORDER = new RETURN("10025", "订单已取消成功，请勿重复操作!");
	public static RETURN CANCEL_ORDER_IS_NOT_PERMISSION = new RETURN("10026", "订单已审批完成，不能取消!");
	public static RETURN GOODS_COUNT_INVALID_IN_ORDER = new RETURN("10027", "订单中商品数量不正确!");
	public static RETURN GOODS_PRICE_CHECK_FAIL = new RETURN("10028", "商品价格校验不通过!");
	public static RETURN PIC_FILE_UPLOAD_FAIL = new RETURN("10029", "图片上传失败!");
	public static RETURN EXPRESS_ADDRESS_NO_FOUND = new RETURN("10030", "配送地址不存在!");
	public static RETURN NO_ORDER_FOUND_ERROR = new RETURN("10031", "订单信息不存在！");
	public static RETURN CHK_GOODSCHANNEL = new RETURN("10032", "该商品没有渠道信息");
	public static RETURN CHK_CUSTOMER_NULLLOGNAME = new RETURN("10033", "用户名不能为空");
	public static RETURN CHK_CUSTOMER_NULLPWD = new RETURN("10034", "密码不能为空");
	public static RETURN NO_USER_USING = new RETURN("10035", "温馨提示:用户状态异常");
	public static RETURN USER_EXIST = new RETURN("10036", "该用户已存在,请更换");
	public static RETURN ORDER_REFUND_NO_FOUND = new RETURN("10037", "订单退款信息不存在");
	public static RETURN REFUND_ORDER_IS_NOT_PERMISSION = new RETURN("10038", "订单已审批完成，不能直接退款，请联系管理员退款!");
	public static RETURN TEMPLATE_NOT_EXISTS = new RETURN("10039", "属性模板不存在!");
	public static RETURN REFUND_PAY_FAIL = new RETURN("10040", "订单退款失败!");
	public static RETURN CUST_SPACE_LOAD_FAIL = new RETURN("10041", "能人空间加载失败！");
	public static RETURN NUMBER_NOTEXIST = new RETURN("10042", "号码不存在");
	public static RETURN NUMBER_SOLDOUT = new RETURN("10043", "号码已售出");
	
	public static RETURN ERROR_FILE_NOT_MATCH = new RETURN("10044", "佣金处理过程出现异常，请联系管理员进行处理。");
	public static RETURN GET_SCORE_FAIL = new RETURN("10045", "获取积分失败！");
	public static RETURN ADD_SCORE_OPT_FAIL = new RETURN("10046", "积分明细添加失败！");
	public static RETURN FILE_WRITE_FAIL = new RETURN("10046", "文件写入出错");
	
	public static RETURN GENERATE_GOODSDETAILPAGE = new RETURN("10047", "商品详情生成失败");
	public static RETURN NONE_MOBILEPHONE = new RETURN("10048", "请选择手机号码");
	
	public static RETURN OLD_PWD_ERROR = new RETURN("10049", "原始密码输入有误！");
	public static RETURN PHONE_ERROR = new RETURN("10050", "输入的手机号有误！");
	public static RETURN NEW_PWD_ERROR = new RETURN("10051", "两次输入的新密码不一致！");
	public static RETURN INPUT_MORE_NUMBER = new RETURN("10052", "绑定手机数量超过规定数量");
	public static RETURN ABLEPERSON_ADUITING = new RETURN("10053", "温馨提示:您的申请正在审核中");
	public static RETURN ABLEPERSON_ADUITINGNOACESS = new RETURN("10054", "您的申请没通过，可以重新申请");
	public static RETURN ABLEPERSON_ADUITINGIDCARD = new RETURN("10055", "您的申请身份证信息不符，请重新上传");
	public static RETURN USER_LOCKED = new RETURN("10056", "您的账号已被冻结");
	public static RETURN IDCARD_EXIST = new RETURN("10057", "该身份证已存在,请更换");
	
	public static RETURN FAIL_FILE_NO_FOUND = new RETURN("10058", "文件未找到！");
	public static RETURN EXIST_NOT_GENERATED = new RETURN("10059", "存在未生成的账期，请先完成账期生成！");
	public static RETURN SYSTEM_PATH_NOT_EXIST = new RETURN("10060", "系统路径不存在,请联系管理员！");
	public static RETURN ERROR_DATA_GENERATED_FAIL = new RETURN("10061", "出错数据生成Excel失败，请稍后再试！");
	public static RETURN BILL_ACCOUNT_EXIST = new RETURN("10062", "佣金账期已存在，请更换账期！");
	public static RETURN SEND_PUSHPAY_ERROR = new RETURN("10063", "发送支付链接失败");
	
	public static RETURN REGISTER_NONESMSCODE = new RETURN("10064", "请输入手机收到的验证码！");
	public static RETURN REGISTER_NOMATCHSMSCODE = new RETURN("10065", "验证码不匹配！");
	public static RETURN SENDSMS_FAILED_MSG = new RETURN("10066", "无效验证码，请重新获取");
	public static RETURN PHONE_FAILED_MSG = new RETURN("10067", "未提供有效联通手机号码，请拨打：4006051186-1 或 通过在线客服 联系管理员重置密码！");
	public static RETURN REGISTER_RECEIVE_NOPHONE = new RETURN("10068", "接收注册验证码手机为空！");
	public static RETURN SENDSMS_FAILED = new RETURN("10069", "短信接口调用成功，接口发送失败，返回failed！");
	public static RETURN SENDSMS_ENCODINGEXCEPTION = new RETURN("10070", "UnsupportedEncodingException！");
	public static RETURN CHARGE_FEE_INTERFACE_ERROR = new RETURN("10071", "老用户续约当前状态验证异常！");
	public static RETURN CHARGE_FEE_STATE_ERROR = new RETURN("10072", "当前号码状态不允许办理此套餐！");
	public static RETURN FILE_UPLOAD_TIPS = new RETURN("10073", "文件已经成功上传至服务器，十五分钟后请检查佣金匹配结果！");
	public static RETURN SENDSMS_FREQUENTLY = new RETURN("10074", "获取验证码过于频繁，请稍候再试");
	public static RETURN UNUNIOM_PHONE = new RETURN("10075", "非联通手机号码");
	public static RETURN CANNOTREBACKDEPOSIT = new RETURN("10076", "有配送订单尚未归档成功，不能申请退还保证金");
	public static RETURN REBACKNOTFILLDAYS = new RETURN("10077", "配送订单归档成功未满三十天，不能申请退还保证金");
	public static RETURN NOCUSTOMER = new RETURN("10078", "无此用户");
	public static RETURN CUSTORPASSERROR = new RETURN("10079", "用户名或密码不正确");
	public static RETURN LOGINBEFORMODPSW = new RETURN("10080", "您的密码已过期，请修改密码");
	public static RETURN NEWANDOLDPSW = new RETURN("10081", "新旧密码不能相同");
	public static RETURN URLERROR = new RETURN("10082", "请求链接有误，请核实后重新查询！");
	public static RETURN LESSONEEXTERNUMBER = new RETURN("10083", "至少需要绑定一张副卡");
	public static RETURN CUSTOMERLEAKPWD = new RETURN("10084", "您的密码属于简单口令，请修改密码");
	public static RETURN CHARGE_FEE_RESULT_NULL = new RETURN("10085", "产品信息接口返回信息为空");
	public static RETURN CARTGOODSALREADYCREATEORDER = new RETURN("10086", "已经生成订单，不能删除，请刷新当前页面");
	
	//第三方能力接口占用用9开头错误编码：（从90000开始）
}
