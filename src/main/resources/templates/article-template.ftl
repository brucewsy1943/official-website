<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <link rel="stylesheet" type="text/css" href="../assets/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/bootstrap-treeview/bootstrap-treeview.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/animate.css" />
    <!-- <link rel="stylesheet" type="text/css" href="../css/assert/print.min.css"/> -->
    <script type="text/javascript">
        document.write('<link rel="stylesheet" type="text/css" href="../css/article-template.css?v='+Math.random()+'"/>');
        document.write('<link rel="stylesheet" type="text/css" href="../css/common/common.css?v='+Math.random()+'"/>');
        document.write('<link rel="stylesheet" type="text/css" href="../css/bootstrap-print.css?v='+Math.random()+'"/>');
    </script>
    <title>${(article.title)!''}</title>

</head>
<body id="for-print">
<header id="hd"></header>
<!-- 	<div id="banner">
        <img src="" />
    </div> -->
<!-- 二级导航栏 -->
<!-- <nav id='secondnavs'></nav> -->

<!-- 路径导航 -->
<div id="path-nav" class="path"></div>

<div id="main-container" class="container-fluid">
    <div class="row">
        <div id="left-part" class="hidden-xs col-sm-2">
            <div id="treeview-selectable"></div>
        </div>
        <!-- 文章部分 -->
        <div id="right-part" class="col-sm-10">
            <div class="article-border">
                <div id="article-section" class="article-section">
                    <div class="article-head">
                        <h2>${(article.title)!''}</h2>
                    </div>
                    <div class="article-subhead">
                        <h3></h3>
                    </div>
                    <div class="article-content">
                        ${(article.content)!''}
                    </div>
                </div>
            </div>
            <div id="recommend" class="clearfix"></div>
        </div>
    </div>
</div>
<!-- 底部 -->
<div id="ft"></div>
</body>
<script src="../assets/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../assets/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="../assets/bootstrap-treeview/bootstrap-treeview.min.js" type="text/javascript" charset="utf-8"></script>

<!-- <script src="../js/assert/print.min.js" type="text/javascript" charset="utf-8"></script> -->
<script type="text/javascript">
    document.write('<script src="../js/common/base.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/common_method.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>')
    document.write('<script src="../js/common/column.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/article.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/util/util.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/article-template.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/tree.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/load-component.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
</script>
</html>
