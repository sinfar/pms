// 复写jquery方法
$.post = function(url, data, success) {
    $.ajax({
        type: "POST",
        url: url,
        data:JSON.stringify(data),
        contentType:"application/json",
        dataType: "json",
        success: function(rst){
            if (rst.success)
                success(rst);
            else
                alert(rst.errMessage);
        },
        error: function(jqXHR){
            alert("发生错误：" + jqXHR.status);
        },
    });
}

//将form表单转换为json数据
$.fn.serializeJson=function(){
    var serializeObj={};
    var array=this.serializeArray(); //将form表单序列化数组对象
    $(array).each(function(){  //遍历表单数组拼接json串
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    return serializeObj;
};
