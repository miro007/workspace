<#include "../include/header.ftl"/>
	<div class="container">6546

	<form class="form-horizontal">
    <div class="control-group">
        <label class="control-label" for="inputType">Type</label>
        <div class="controls">
            <input type="text" id="inputType" placeholder="Type">
        </div>
    </div>
    <div class="control-group">
        <span class="control-label">Metadata</span>
        <div class="controls form-inline">
            <label for="inputKey">Key</label>
            <input type="text" class="input-small" placeholder="Key" id="inputKey">
            <label for="inputValue">Value</label>
            <input type="password" class="input-small" placeholder="Value" id="inputValue">
        </div>
    </div>
<div class="control-group">
  <label class="control-label" for="FirstName">Name</label>
    <div class="controls row-fluid">
        <div class="span2 row"><input class="span12" id="FirstName" name="FirstName" placeholder="Firstname" type="text" value="" /></div>
        <div class="span2 row"><input class="span12" id="MiddleName" name="MiddleName" placeholder="Middlename" type="text" value="" /></div>
        <div class="span2 row"><input class="span12" id="LastName" name="LastName" placeholder="Lastname" type="text" value="" /></div>
        <p class="help-block span12 row" style="color: red;">How do I make these 3 input's (together) equal width of the "Adresse" input.</p>
    </div>
</div>
<div class="control-group">
  <label class="control-label" for="AddressLine1">Adresse</label>
  <div class="controls row-fluid">
    <div class="span6 row"><input class="span12" id="AddressLine1" name="AddressLine1" placeholder="AddressLine1" type="text" value="" /></div>
  </div>
    </div>

    <div class="control-group row-fluid">
  <label class="control-label" for="FirstName2">Name2</label>
    <div class="controls">
        <div class="span2 row"><input class="span12" id="FirstName2" name="FirstName" placeholder="Firstname" type="text" value="" /></div>
    </div>
     <label class="control-label" for="FirstName3">Name3</label>
    <div class="controls">
        <div class="span2 row"><input class="span12" id="FirstName3" name="FirstName" placeholder="Firstname" type="text" value="" /></div>
    </div>
</div>
</form>

<#import "tag.ftl" as str />1


<@spring.url value="/something"/> ${springMacroRequestContext}
<@str.hello name="mirek" bean="user" field="username"/>
		<#if RequestParameters['authfail']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-error">
						Login failed
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['accessdenied']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-error">
						Access denied
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['login']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-warning">
						You are not signed in, please sign in first.
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['loggedout']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-success">
						You have been logged out.
					</div>
				</div>
			</div>
		</#if>

		<div class="row">
			<div class="span12">
				<fieldset>
					<legend>Hello, World!</legend>
				</fieldset>
			</div>
		</div>

		<footer>
			<p>Built with Maven profile ${buildEnv}</p>
		</footer>
	</div> <!-- /container -->
<#include "../include/footer.ftl"/>
