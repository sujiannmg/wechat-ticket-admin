<!DOCTYPE html>
<html>
<head>
  <#include "../commons-pages/commons-page-head.ftl">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <#include "../commons-pages/commons-top-nav.ftl">
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <#include "../commons-pages/commons-nav.ftl">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <ol class="breadcrumb" style="margin-bottom: 0px">
      <li><a href="${webContextPath!}/starter"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="${webContextPath!}/account"><i class="fa fa-user"></i> 账号管理</a></li>
      <li class="active"><i class="fa fa-edit"></i> 增加账号</li>
    </ol>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">编辑帐号信息</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <#if error ?? >
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <strong>程序运行异常：</strong> ${error!}
                </div>
              </#if>
              <form id="accountEditForm" method="POST" action="${webContextPath!}/account/edit" class="form-horizontal dss-base-form">
                <input type="hidden" name="accountId" <#if accountInfo ??>value="${accountInfo.getAccountId()!}"</#if> >
                <input type="hidden" name="version" <#if accountInfo ??>value="${accountInfo.getVersion()!}"</#if>>
                <div class="form-group"><label class="col-sm-2 control-label">系统帐号</label>
                  <div class="col-sm-8">
                    <input type="text" name="account"  class="form-control" <#if accountInfo ??>value="${accountInfo.getAccount()!}"</#if>>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">系统密码</label>
                  <div class="col-sm-8">
                    <input type="password" name="password"  class="form-control" <#if accountInfo ??>value="${accountInfo.getPassword()!}"</#if>>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">账号描述</label>
                  <div class="col-sm-8">
                    <input type="text" name="description"  class="form-control" <#if accountInfo ??>value="${accountInfo.getDescription()!}"</#if>>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-2 col-sm-offset-10">
                    <button class="btn btn-sm btn-primary" type="submit">保存</button>
                    <a class="btn btn-sm btn-white" href="${webContextPath!}/account/">取消</a>
                  </div>
                </div>
              </form>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <#include "../commons-pages/commons-footer.ftl">

  <!-- Control Sidebar -->
  <#include "../commons-pages/commons-right-nav.ftl">
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<#include "../commons-pages/commons-body-script.ftl">
<script type="text/javascript">
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-red',
      radioClass: 'iradio_square-red',
      increaseArea: '20%' // optional
    });
    $("#accountEditForm").bootstrapValidator({
      message: '请输入用户名/密码',
      fields: {
        account: {
          validators: {
            notEmpty: {
              message: '登录用户名不能为空'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: '密码不能为空'
            }
          }
        }
      }
    });
  });
</script>
</body>
</html>
