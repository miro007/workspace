<#macro hello name bean field>
witaj ${name} ${bean} ${field}
<i>${"${bean+'.'+field}"?eval}</i>
</#macro>