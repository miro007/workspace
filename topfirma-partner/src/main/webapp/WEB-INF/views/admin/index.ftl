<#include "../include/header.ftl"/>

<table class="table">
<tr>
	<td>Login</td>
	<td>First Name</td>
	<td>Last Name</td>
</tr>
<#list operators as operator>
<tr>
	<td>${operator.login!}</td>
	<td>${operator.firstName!}</td>
	<td>${operator.lastName!}</td>
<tr>
</#list>
</table>

<#include "../include/footer.ftl"/>
