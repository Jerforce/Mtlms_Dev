<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="js/H-ui.js" type="text/javascript"></script>
    <title>添加评估选项</title>
</head>

<body>
<div class="margin clearfix">
    <div class="article_style" style="width: 60%; margin: auto;">
        <form action="" method="post">
            <div class="add_content" id="form-article-add">
                <ul>

                    <li class="clearfix"><label class="label_name"><i>*</i>所属类目</label>
                        <span class="formControls col-4">
                           <select class="form-control" id="basicInfoSelect">
                              <option value="0">--选择所属类目--</option>
                           </select>
                        </span>
                    </li>

                    <li class="clearfix Mandatory">
                        <label class="label_name"><i>*</i>选项名称</label>
                        <span class="formControls col-10">
                          <input name="" type="text" id="form-field-1" class="col-xs-10 col-sm-12 ">
                        </span>
                    </li>

                    <li class="clearfix Mandatory">
                        <label class="label_name"><i>*</i>选项描述</label>
                        <span class="formControls col-10">
				<input name="" type="text" id="form-field-2" class="col-xs-10 col-sm-12 ">
			</span>
                    </li>

                    <li class="clearfix"><label class="label_name">是否显示</label>
                        <span class="formControls col-10">
                        <span class="add_date l_f">
                           <label><input name="radio" type="radio" class="ace" checked="checked"><span class="lbl">显示</span></label>&nbsp;
                           <label><input name="radio" type="radio" class="ace" ><span class="lbl">不显示</span></label></span>
                        </span>
                    </li>

                </ul>
                <div class="Button_operation">
                    <button  class="btn btn-primary radius" type="button" id="submitBtn">提交</button>
                    <button  class="btn btn-default radius" type="button">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    $(function(){
        //ajax请求评估类目列表
        $.post("BasicInfoLoadServlet",function(res){
            //显示评估类目列表
            if(res.code == 1000){
                var basicInfoArr = res.data;
                for(var i=0; i<basicInfoArr.length; i++){
                    var basicInfo = basicInfoArr[i];
                    var optionStr = "<option value='"+basicInfo.basicInfoId+"'>"+basicInfo.basicInfoName+"</option>";
                    console.log(optionStr);
                    $("#basicInfoSelect").append(optionStr);
                }
            }
        },"json");
    });

    $("#submitBtn").click(function(){
        //获取用户输入
        var params = {};
        params.basicInfoId = $("#basicInfoSelect").val();   //获取选择的评估类目ID
        params.infoDetailName = $("#form-field-1").val();   //获取输入的评估选项名称
        params.infoDetailDesc = $("#form-field-2").val();   //获取输入的评估选项描述
        //ajax请求提交评估选项数据到 InfoDetailAddServlet
        $.post("InfoDetailAddServlet",params,function(res){
            //处理添加评估选项的回调
            if(res.code == 1000){
                layer.msg("评估类别添加成功",{
                    icon:1,
                    time:1000
                });
                setTimeout(function(){
                    window.location.href = "basic_info_detail.jsp";
                },1000);
            }else if(res.code == 1001){
                layer.msg("评估类别添加失败",{
                    icon:5,
                    time:1000
                });
            }
        },"json");
    });

</script>
