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
      <li><a href="${webContextPath!}/wechatuserinfoadmin"><i class="fa fa-ticket"></i> 影票订单信息管理</a></li>
      <li class="active"><i class="fa fa-list-alt"></i> 影票订单信息详情</li>
    </ol>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">详细影票订单信息</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <#if error ?? >
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <strong>程序运行异常：</strong> ${error!}
                </div>
              </#if>
              <form id="accountEditForm" method="POST" action="${webContextPath!}/ticketorderinfoadmin/edit/" class="form-horizontal dss-base-form">
                <div class="form-group"><label class="col-sm-2 control-label">影票订单编号</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getOpenId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影院名称</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getNickName()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影院位置</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getGender()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影片名称</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getCity()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影片开始时间</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getProvince()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影片标签</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getAvatarUrl()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">观影具体位置</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getUnionId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影片订单号</label>
                  <div class="col-sm-8">
                      <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getAvatarUrl()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">用户手机号</label>
                  <div class="col-sm-8">
                      <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getUnionId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">影片流水号</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getUnionId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">订单验证码</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getUnionId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">订单总价</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getUnionId()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">业务版本号</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getVersion()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">创建用户</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getCreateUsername()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">创建时间</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getCreateTime()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">最后修改用户</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getModifyUsername()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group"><label class="col-sm-2 control-label">最后修改时间</label>
                  <div class="col-sm-8">
                    <label class="form-control"> <#if wechatUserInfo ??> ${wechatUserInfo.getModifyTime()!}</#if> </label>
                  </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                  <div class="col-sm-2 col-sm-offset-10">
                    <a class="btn btn-sm btn-primary" href="${webContextPath!}/wechatuserinfoadmin/">返回</a>
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
