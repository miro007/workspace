<form class="col-md-12" role="form" action="save" method="post">
	<div class="has-error  form-group"><label class="control-label"> ${error!}</label></div>
		<@form.row >
			<@form.input id="firstName" label="Imie" value="Imie" />
			<@form.input id="lastName" label="Nazwisko" value="Nazwisko" required="false" />
			<@form.spacer/>
			<@form.input id="age" label="Wiek" value="WIek" />
		</@form.row>
		<@form.row columns="1">
			<@form.input id="firstName" label="Imie" value="Imie" />
			<@form.input id="lastName" label="Nazwisko" value="Nazwisko" required="false" />
			<@form.input id="age" label="Wiek" value="WIek" />
		</@form.row>
		<@form.row columns="3">
			<@form.input id="firstName" label="Imie" value="Imie" />
			<@form.input id="lastName" label="Nazwisko" value="Nazwisko" required="false" />
			<@form.spacer/>
			<@form.spacer/>
			<@form.input id="age" label="Wiek" value="WIek" />
		</@form.row>
		<button class="btn" type="submit">Zapisz</button>
	</form>