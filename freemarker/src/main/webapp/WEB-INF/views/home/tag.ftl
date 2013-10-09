<#macro hello name bean field>
witaj ${name} ${bean} ${field}
${"${bean+'.'+field}"?eval}
</#macro>