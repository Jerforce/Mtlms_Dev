<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="font/css/font-awesome.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="js/dragDivResize.js" type="text/javascript"></script>
    <title>添加商品</title>
</head>

<body>
<div class="Competence_add_style clearfix">
    <form action="GoodsAddServlet" method="post">
        <!--商品信息-->
        <div class="left_Competence_add">
            <div class="title_name">添加商品</div>
            <div class="Competence_add">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"> 一级分类 </label>
                    <div class="col-sm-9">
                        <select class="col-xs-10 col-sm-10" id="categorySelect">
                            <option value="">请选择分类</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 商品品牌 </label>
                    <div class="col-sm-9">
                        <select class="col-xs-10 col-sm-10" name="brandId" id="brandSelect">
                            <option value="">请选择品牌</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-3"> 商品名称 </label>
                    <div class="col-sm-9">
                        <input type="text" id="form-field-3" name="goodsName" placeholder="商品名称" class="col-xs-10 col-sm-10">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > </label>
                    <div class="col-sm-9"><img id="reviewImg" src="" height="50"/></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 商品图片 </label>
                    <div class="col-sm-9">
                        <input type="hidden" id="goodsImgPath" name="goodsImgPath"/>
                        <input type="file" id="goodsImg" class="col-xs-10 col-sm-10" onchange="uploadImg()">
                    </div>
                </div>
                <script type="text/javascript">
                    function uploadImg(){
                        var file = $("#goodsImg")[0].files[0];
                        var formData = new FormData();
                        //因为我们之前写的ImageUploadServlet是用来上传品牌logo的，因此接收文件使用的key为brandLogoImg
                        //所以我们如果上传商品也想复用ImageUploadServlet类，提交文件的key也要使用brandLogoImg
                        formData.append("brandLogoImg",file);
                        $.ajax({
                            type:"post",
                            url:"ImageUploadServlet",
                            data:formData,
                            processData:false,
                            contentType:false,
                            success:function(res){
                                if(res.code == 1000){
                                   $("#reviewImg").attr("src", res.msg);
                                   $("#goodsImgPath").val(res.msg);
                                }
                            }
                        });
                    }
                </script>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-5"> 最高回收价 </label>
                    <div class="col-sm-9">
                        <input type="text" id="form-field-5" name="goodsCost" placeholder="最高回收价" class="col-xs-10 col-sm-10">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-6"> 最低回收价 </label>
                    <div class="col-sm-9">
                        <input type="text" id="form-field-6" name="goodsMinPrice" placeholder="最低回收价" class="col-xs-10 col-sm-10">
                    </div>
                </div>
                <!--按钮操作-->
                <div class="Button_operation">
                    <button  class="btn btn-primary radius" type="submit"><i class="fa fa-save "></i> 提交</button>
                    <button  class="btn btn-default radius" type="button">取消</button>
                </div>
            </div>
        </div>
        <!--评估配置-->
        <div class="Assign_style">
            <div class="title_name">评估配置</div>
            <div class="Select_Competence" style="padding: 10px 20px;">
                <c:forEach items="${basicInfoList}" var="basicInfo">
                    <h5 style="background: lightgray; padding: 5px;">${basicInfo.basicInfoName}</h5>
                    <c:forEach items="${basicInfo.infoDetailList}" var="infoDetail">
                        <div style="padding: 3px 10px;">
                            <input type="checkbox" name="infoDetailId" value="${infoDetail.infoDetailId}"/>
                            <label style="padding-left: 10px;">${infoDetail.infoDetailName}</label>
                            <input type="text" name="price_${infoDetail.infoDetailId}" placeholder="选项扣除金额" style="height: 20px;" />
                        </div>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    //初始化宽度、高度
    $(".left_Competence_add,.Competence_add_style").height($(window).height()).val();;
    $(".Assign_style").width($(window).width()-500).height($(window).height()).val();
    $(".Select_Competence").width($(window).width()-500).height($(window).height()-40).val();
    //当文档窗口发生改变时 触发
    $(window).resize(function(){

        $(".Assign_style").width($(window).width()-500).height($(window).height()).val();
        $(".Select_Competence").width($(window).width()-500).height($(window).height()-40).val();
        $(".left_Competence_add,.Competence_add_style").height($(window).height()).val();;
    });
</script>
<script type="text/javascript">
    $(function(){
        //1.加载一级分类
        $.post("CategoryListForAjaxServlet",function(res){
            if(res.code == 1000){
                var categoryArr = res.data;
                for(var i=0 ; i < categoryArr.length; i++){
                    var category = categoryArr[i];
                    var optionStr = "<option value='"+category.categoryId+"'>"+category.categoryName+"</option>";
                    $("#categorySelect").append(optionStr);
                }
            }
        },"json");
    });

    //监听一级分类下来菜单change事件
    $("#categorySelect").change(function(){
        var cid = $("#categorySelect").val();
        //当显示一个分类下的品牌列表时，先将品牌下来菜单清空
        $("#brandSelect").html("<option value=''>请选择商品品牌</option>");
        if(cid != ""){
            //发送ajax请求到BrandListForAjaxServlet,通过分类ID，查询品牌列表
            $.post("BrandListForAjaxServlet",{categoryId:cid},function(res){
                //显示响应的品牌列表
                if(res.code == 1000){
                    var brandArr = res.data;
                    //遍历显示品牌下拉菜单
                    for(var i=0; i<brandArr.length; i++){
                        var brand = brandArr[i];
                        var optionStr = "<option value='"+brand.brandId+"'>"+brand.brandName+"</option>";
                        $("#brandSelect").append(optionStr);
                    }
                }else if(res.code == 1001){
                    layer.msg("品牌信息加载失败！");
                }
            },"json");
        }
    });

</script>