<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <link rel="stylesheet" type="text/css" href="../assets/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/animate.css" />
    <!-- <link rel="stylesheet" type="text/css" href="../css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/article-template.css"/> -->
    <script type="text/javascript">
        document.write('<link rel="stylesheet" type="text/css" href="../css/common/common.css?v='+Math.random()+'"/>');
        document.write('<link rel="stylesheet" type="text/css" href="../css/article-template.css?v='+Math.random()+'"/>');
        document.write('<link rel="stylesheet" type="text/css" href="../css/bootstrap-print.css?v='+Math.random()+'"/>');
    </script>
    <title>苏研院官网</title>
    <style type="text/css">
        .tit {
            padding-top: 10px;
            font-size: 1.8rem;
            font-weight: bold;
            color: #666;
            text-align: left;
            line-height: 30px;
            text-align: center;
            padding-bottom: 10px;
            /* height: 80px; */
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 90%;
            margin-left: auto;
            margin-right: auto;
        }
        .add-margin-top{
            margin-top: 30px;
        }
        .add-border{
            display: block;
            border: 1px solid #ECECEC;
        }

        #relatedbusiness{
            max-width: 1350px;
            background: #FFFFFF;
            padding-bottom: 30px;
            padding-top: 10px;
            margin-bottom: 50px;
            margin-left: auto;
            margin-right: auto;
        }
        #relatedbusiness .col-md-3{
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<header id="hd"></header>
<header id="hd"></header>
<!-- <div id="banner">
    <img class="banner" src="" />
</div> -->
<!-- 二级导航栏 -->
<nav id='secondnavs'></nav>
<!-- 路径导航 -->
<div id="path-nav"></div>
<div id="main-container" class="container-fluid">
    <div class="row">
        <div id="left-part" class="hidden-xs col-sm-2">
            <div id="treeview-selectable"></div>
        </div>
        <div class="col-sm-10">
            <div id="cell-article-border" class="article-border">
                <div id="cell-aticle-section" class="article-section">
                    <div class="article-head">
                        <h2>${(article.title)!''}</h2>
                    </div>
                    <div class="article-subhead">
                        <h3></h3>
                    </div>
                    <div class="article-pubtime">
                        <h4></h4>
                    </div>
                    <div class="article-content">
                         ${(article.content)!''}
                    </div>
                </div>
            </div>
            <div id="relatedbusiness" class="clearfix">

            </div>

        </div>
        <!-- <div id="first-four" class="col-md-10 col-md-offset-1 add-margin-top"></div>
        <div id="last-four" class="col-md-10 col-md-offset-1 add-margin-top"></div> -->
    </div>
</div>
<div id="ft"></div>
</body>
<script src="../assets/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../assets/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="../assets/bootstrap-treeview/bootstrap-treeview.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    document.write('<script src="../js/common/base.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/common_method.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>')
    document.write('<script src="../js/util/util.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/article.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/column.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/technology-article-list.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/tree.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
    document.write('<script src="../js/common/load-component.js?v='+new Date().getTime()+'" type="text/javascript" charset="utf-8"><\/script>');
</script>
</html>
