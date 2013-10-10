<#include "../include/header.ftl"/>
<form role="form" action="login" method="POST">
  <div class="form-group" >
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" required="required" value="${admin.email!}" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" required="required" value="${admin.password!}" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>

<#include "../include/footer.ftl"/>
