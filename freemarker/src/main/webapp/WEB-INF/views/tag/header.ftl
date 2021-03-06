<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
		<#include "common.ftl" />
		<#import "window.ftl" as window />
		<#import "tag.ftl" as form/>

		<@window.deleteConfirm />
        <meta charset="utf-8">
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<@spring.url value="/css/main.css"/>">

        <script src="<@spring.url value="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/bootstrap.min.js"/>"></script>
        <script src="<@spring.url value="/js/main.js"/>"></script>


        <script src="<@spring.url value="/js/vendor/angular.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-animate.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-cookies.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-loader.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-resource.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-route.min.js"/>"></script>
        <script src="<@spring.url value="/js/vendor/angular-sanitize.min.js"/>"></script>
    </head>
    <body>
    <img alt="" src="https://www.firmino.pl/tl_files/topfirma/obrazki/logo-firmino.png" style="float:left">
    <br/>
    <div class="container">
    <#if logged!true >
    <nav class="navbar navbar-default" role="navigation">
      <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<@spring.url value='/admin/index' />">Panel administora</a>
  </div>

<div class="collapse navbar-collapse navbar-ex1-collapse ">
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Partnerzy</a></li>
      <li><a href="#">Biura rachunkowe</a></li>
      <li><a href="#">Materialy reklamowe</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Wyloguj <span class="glyphicon glyphicon-off"></a></li>
    </ul>
  </div><!-- /.navbar-collapse -->
  </nav>
</#if>
<br/>