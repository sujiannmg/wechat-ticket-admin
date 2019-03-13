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
      <!-- <li><a href="${webContextPath!}/account">账号管理</a></li> -->
      <li class="active"><i class="fa fa-user"></i> 账号管理</li>
    </ol>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">账号信息列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <a class="btn btn-sm btn-success" style="width: 80px" href="${webContextPath!}/account/create"><i class="fa fa-plus"></i>增加帐号</a>
              <table id="example2" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>系统帐号</th>
                  <th>账号描述</th>
                  <th>业务版本</th>
                  <th>创建时间</th>
                  <th>修改时间</th>
                  <th width="95">操作</th>
                </tr>
                </thead>
                <tbody>
                <#if accountInfoList ??>
                <#list accountInfoList as account>
                  <tr>
                    <td>${account.getAccount()!}</td>
                    <td>${account.getDescription()!}</td>
                    <td>${account.getVersion()!}</td>
                    <td>${account.getCreateTime()!}</td>
                    <td>${account.getModifyTime()!}</td>
                    <td>
                      <a class="btn btn-xs btn-primary" href="${webContextPath!}/api/base/account/view/${account.getAccountId()!}">详情</a>
                      <a class="btn btn-xs btn-primary" href="${webContextPath!}/api/base/account/edit/${account.getAccountId()!}">修改</a>
                      <button type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#deleteAccount${account.getAccountId()!}">删 除</button>
                    </td>
                  </tr>
                </#list>
                </#if>
                </tbody>
                <tfoot>
                <tr>
                  <th>系统帐号</th>
                  <th>账号描述</th>
                  <th>业务版本</th>
                  <th>创建时间</th>
                  <th>修改时间</th>
                  <th>操作</th>
                </tr>
                </tfoot>
              </table>
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
<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
</body>
</html>
