<#include "tag/header.ftl"/>



<@window.tabDefinition id="demo">
	<@window.tabHeader id="form" title="Formularz" />
	<@window.tabHeader id="modal" title="Potwierdzenie usuwania" />
	<@window.tabHeader id="panel" title="Panel" />
	<@window.tabHeader id="table" title="Tabelka" />
</@window.tabDefinition>

<@window.tabContent id="demo">
	<@window.tab id="form" >
		<#include "form.ftl"/>
	</@window.tab>
	<@window.tab id="modal" >
		<button class="btn btn-primary" onclick="showDeleteConfirmDialog('link')">MOdal</button>
	</@window.tab>
	<@window.tab id="panel" >
		<@window.panel title="Naglowek panela" >
			sadasdsad
		</@window.panel>
	</@window.tab>
	<@window.tab id="table" >
		<#include "table.ftl"/>
	</@window.tab>
</@window.tabContent>

<#include "tag/footer.ftl"/>
