<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <!-- page specific plugin scripts -->
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script type="text/javascript" src="Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
    <script src="js/lrtk.js" type="text/javascript"></script>
    <title>商品列表</title>
</head>
<body>
<div class=" page-content clearfix">
    <div id="products_style">
        <div class="search_style">

            <ul class="search_content clearfix">
                <li>
                    <label class="l_f" style="margin-right: 10px;">一级分类</label>
                    <select class="text_add" id="categorySelect">
                        <option value="">请选择商品分类</option>
                    </select>
                </li>
                <li>
                    <label class="l_f" style="margin-right: 10px;">商品品牌</label>
                    <select class="text_add" id="brandSelect">
                        <option value="">请选择商品品牌</option>
                    </select>
                </li>
                <li style="width:90px;">
                    <button type="button" class="btn_search"><i class="icon-search"></i>查询</button>
                </li>
            </ul>
        </div>
        <div class="border clearfix">
           <span class="l_f">
            <a href="BasicInfoAndDetailListServlet" title="添加商品" class="btn btn-warning Order_form">
                <i class="icon-plus"></i>添加商品
            </a>
            <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
           </span>
            <span class="r_f">${tips}</span>
        </div>
        <div class="table_menu_list clearfix" id="testIframe" style="width:100%">
            <table class="table table-striped table-bordered table-hover" id="sample-table">
                <thead>
                <tr>
                    <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
                    <th width="80px">商品编号</th>
                    <th width="250px">商品名称</th>
                    <th width="100px">商品图片</th>
                    <th width="100px">最高回收价</th>
                    <th width="100px">最低回收价</th>
                    <th width="70px">商品状态</th>
                    <th width="200px">操作</th>
                </tr>
                </thead>
                <tbody id="goodsListTbody">
                <tr>
                    <td colspan="8" align="center"><label>请选择商品分类及品牌！</label></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
</body>
</html>
<script type="text/javascript">
    //初始化宽度、高度
    $(".widget-box").height($(window).height() - 215);
    $(".table_menu_list").width($(window).width() - 30);
    $(".table_menu_list").height($(window).height() - 215);
    //当文档窗口发生改变时 触发
    $(window).resize(function () {
        $(".widget-box").height($(window).height() - 215);
        $(".table_menu_list").width($(window).width() - 30);
        $(".table_menu_list").height($(window).height() - 215);
    })
</script>
<script type="text/javascript">
    <%--一级菜单--%>
    <%--当前文档加载后执行--%>
    $(function () {
        //通过ajax请求访问到CategoryListForAjaxServlet ,将响应的内容添加到子标签中
        $.post("CategoryListForAjaxServlet", function (res) {
            if (res.code == 1000) {
                //categorArr返回的数据
                var categorArr = res.data;
                for (var i = 0; i < categorArr.length; i++) {
                    //categorArr[i]表示返回的每一条数据
                    var optionStr = "<option value='" + categorArr[i].categoryId + "'>" + categorArr[i].categoryName + "</option>"
                    $("#categorySelect").append(optionStr);
                }
            } else {
                //消息提示
                layer.msg("未加载分类信息...")
            }

        })
    })

    //====================================================================================================

    //监听一级分类下来菜单change事件
    //下拉列表
    $("#categorySelect").change(function () {
        //获得分类的id选中的id
        var cid = $("#categorySelect").val();
        //清空之前的下拉列表内容
        $("#brandSelect").html("<option value=''>请选择商品品牌</option>")
        //请求
        if (cid != "") {
            $.post("BrandListForAjaxServlet", {categoryId: cid}, function (res) {
                if (res.code == 1000) {
                    //categorArr返回的数据
                    var brandArr = res.data;
                    for (var i = 0; i < brandArr.length; i++) {
                        //categorArr[i]表示返回的每一条数据
                        var optionStr = "<option value='" + brandArr[i].brandId + "'>" + brandArr[i].brandName + "</option>"
                        $("#brandSelect").append(optionStr);
                    }
                } else {
                    //消息提示
                    layer.msg("未加载品牌信息...")
                }
            }, "json")
        }
    })
    //============================================================================================
    // <label class="l_f" style="margin-right: 10px;">商品品牌</label>
    // <select class="text_add" id="brandSelect">
    //     <option value="">请选择商品品牌</option>
    // </select>

    $(".btn_search").click(function () {
        //1.获取选择的品牌ID
        var bid = $("#brandSelect").val();
        if (bid == "") {
            layer.msg("请选择品牌！", {
                icon: 5,
                time: 1000
            });
        } else {
            //2.发送ajax请求，查询商品信息
            $.post("GoodsListForAjaxServlet", {brandId: bid}, function (res) {
                //3.显示返回的商品信息
                if (res.code == 1000) {
                    var goodsArr = res.data;
                    //显示商品列表之前先清空表格
                    $("#goodsListTbody").html("");
                    for (var i = 0; i < goodsArr.length; i++) {
                        var goods = goodsArr[i];
                        var trStr = "<tr>" +
                            "<td width='25px'><label><input type='checkbox' class='ace' value='“+goods.goodsId+”' name='goodsId' ><span class='lbl'></span></label></td>" +
                            "<td width='80px'>" + goods.goodsId + "</td>" +
                            "<td width='250px'><u style='cursor:pointer' class='text-primary' onclick=''>" + goods.goodsName + "</u></td>" +
                            "<td width='100px'><img src='" + goods.goodsImg + "' height='40'/></td>" +
                            "<td width='100px'>￥" + goods.goodsCost + ".00</td>" +
                            "<td width='100px'>￥" + goods.goodsMinPrice + ".00</td>" +
                            "<td class='td-status'><span class='label label-success radius'>已启用</span></td>" +
                            "<td class='td-manage'>" +
                            "<a  href='javascript:;' title='停用'  class='btn btn-xs btn-success'><i class='icon-ok bigger-120'></i></a>" +
                            "<a title='编辑'  href='javascript:;'  class='btn btn-xs btn-info' ><i class='icon-edit bigger-120'></i></a>" +
                            "<a title='删除' href='javascript:;'   class='btn btn-xs btn-warning' ><i class='icon-trash  bigger-120'></i></a>" +
                            "</td>" +
                            "</tr>"
                        $("#goodsListTbody").append(trStr);
                    }
                }
            }, "json")
        }
    });

    //批量删除
    function ovid() {
        //定义一个数组，用来存放选中的商品id
        var goodIds = new Array();
        //获得选中的checkbox中的商品id
        $("input[name='goodsId']:checked").each(function () {
            goodIds.push($(this).val());
        })
        goodIds = goodIds.toString();//转成字符串
        //将这些id发送给DelGoodAllByIdServlet 进行后端删除
        $.post("DelGoodAllByIdServlet", {goodIds: goodIds}, function (res) {
            //获得响应编码res.code
            if(res.code==1000){
                //根据响应编码进行前端删除
                $("input[name='goodsId']:checked").parent().parent().parent().remove();
                }
            })

    }
</script>
