(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend($.validator.messages, {
	required: "这是必填字段",
	remote: "请修正此字段",
	email: "请输入有效的电子邮件地址",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	dateISO: "请输入有效的日期 (YYYY-MM-DD)",
	number: "请输入有效的数字",
	digits: "只能输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format("最多可以输入 {0} 个字符"),
	minlength: $.validator.format("最少要输入 {0} 个字符"),
	rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
	range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
	max: $.validator.format("请输入不大于 {0} 的数值"),
	min: $.validator.format("请输入不小于 {0} 的数值")
});


// 手机号码验证
	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "手机号码格式错误");

// 电话号码验证
	jQuery.validator.addMethod("phone", function(value, element) {
		var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		return this.optional(element) || (tel.test(value));
	}, "电话号码格式错误");

// 邮政编码验证
	jQuery.validator.addMethod("zipCode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "邮政编码格式错误");

// QQ号码验证
	jQuery.validator.addMethod("qq", function(value, element) {
		var tel = /^[1-9]\d{4,9}$/;
		return this.optional(element) || (tel.test(value));
	}, "qq号码格式错误");

// IP地址验证
	jQuery.validator.addMethod("ip", function(value, element) {
		var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
		return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
	}, "Ip地址格式错误");

// 字母和数字的验证
	jQuery.validator.addMethod("chrnum", function(value, element) {
		var chrnum = /^([a-zA-Z0-9]+)$/;
		return this.optional(element) || (chrnum.test(value));
	}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
	jQuery.validator.addMethod("chinese", function(value, element) {
		var chinese = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "只能输入中文");

// 下拉框验证
	$.validator.addMethod("selectNone", function(value, element) {
		return value == '' || value==null || value == undefined;
	}, "必须选择一项");

// 字节长度验证
	jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
		var length = value.length;
		for (var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element) || (length >= param[0] && length <= param[1]);
	}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));

}));
