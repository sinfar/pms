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


function listToTree(oldArr){
    oldArr.forEach(element => {
        let parentId = element.parentId;
    if(parentId !== 0){
        oldArr.forEach(ele => {
            if(ele.id == parentId){ //当内层循环的ID== 外层循环的parendId时，（说明有children），需要往该内层id里建个children并push对应的数组；
            if(!ele.children){
                ele.children = [];
            }
            ele.children.push(element);
        }
    });
    }
});
    console.log(oldArr) //此时的数组是在原基础上补充了children;
    oldArr = oldArr.filter(ele => ele.parentId === 0 || ele.parentId == null); //这一步是过滤，按树展开，将多余的数组剔除；
    console.log(oldArr)
    return oldArr;
}

var simpleEditor = {
    basePath: '/lib/zui/lib/kindeditor/',
    bodyClass : 'article-content',     // 确保编辑器内的内容也应用 ZUI 排版样式
    cssPath: '/lib/zui/css/zui.css', // 确保编辑器内的内容也应用 ZUI 排版样式
    resizeType : 1,
    allowPreviewEmoticons : false,
    allowImageUpload : false,
    items : [
        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
        'insertunorderedlist', '|', 'emoticons', 'image', 'link'
    ]
}
