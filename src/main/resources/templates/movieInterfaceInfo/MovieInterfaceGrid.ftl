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
      <li class="active"><i class="fa fa-file-movie-o"></i> 电影接口信息管理</li>
    </ol>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">电影接口信息列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <a class="btn btn-sm btn-success disabled " style="width: 130px" href="${webContextPath!}/movieinterface/create"><i class="fa fa-plus"></i>增加电影接口信息</a>
              <!-- <button type="button" data-toggle="tooltip" data-original-title="点击修改" class="btn btn-info btn-xs" onclick="">修改</button> -->
              <table id="example2" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>接口名称</th>
                  <th>接口类型</th>
                  <th>接口地址</th>
                  <th>接口参数</th>
                  <th>接口编码</th>
                  <th>响应状态</th>
                  <th>调用次数</th>
                  <th>调用时间</th>
                  <th>创建时间</th>
                  <th width="95">操作</th>
                </tr>
                </thead>
                <tbody>
                <#if movieInterfaceInfoList ??>
                <#list movieInterfaceInfoList as movieInterfaceInfo>
                  <tr>
                    <td>${movieInterfaceInfo.getMovieInterfaceName()!}</td>
                    <td>${movieInterfaceInfo.getMovieInterfaceType()!}</td>
                    <td>${movieInterfaceInfo.getMovieInterfaceUrl()!}</td>
                    <td>${movieInterfaceInfo.getMovieInterfacePaprm()!}</td>
                    <td>${movieInterfaceInfo.getMovieInterfaceAscii()!}</td>
                    <td>${movieInterfaceInfo.getMovieInterfaceCode()!}</td>
                    <td>${movieInterfaceInfo.getMovieReponseNum()!}</td>
                    <td>${movieInterfaceInfo.getMovieReponseTime()!}</td>
                    <td>${movieInterfaceInfo.getCreateTime()!}</td>
                    <td>
                      <a class="btn btn-xs btn-primary" href="${webContextPath!}/movieinterface/view/${movieInterfaceInfo.getMovieInterfaceId()!}">详情</a>
                      <a class="btn btn-xs btn-primary disabled" href="${webContextPath!}/movieinterface/edit/${movieInterfaceInfo.getMovieInterfaceId()!}">修改</a>
                      <button type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#deleteAccount${movieInterfaceInfo.getMovieInterfaceId()!}">删 除</button>
                      <div class="modal fade" id="deleteAccount${movieInterfaceInfo.getMovieInterfaceId()!}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                              <h5 class="modal-title" id="myModalLabel">确认删除</h5>
                            </div>
                            <div class="modal-body">
                              <p class="text-danger">请注意！请注意！请注意！</p>
                              <p class="text-info">确认删除该系统帐号 <strong style="color: red"> ${movieInterfaceInfo.getMovieInterfaceName()!} </strong> 吗？</p>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-sm btn-primary pull-left" data-dismiss="modal">取 消</button>
                              <!--<button type="button" class="btn btn-primary">Save changes</button> -->
                              <a class="btn btn-sm btn-danger" id="deleteButton" data-loading-text="删除中..." href="${webContextPath!}/movieinterface/delete/${movieInterfaceInfo.getMovieInterfaceId()!}">继续删除</a>
                            </div>
                          </div>
                          <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                      </div>
                    </td>
                  </tr>
                </#list>
                </#if>
                </tbody>
                <tfoot>
                <tr>
                  <th>接口名称</th>
                  <th>接口类型</th>
                  <th>接口地址</th>
                  <th>接口参数</th>
                  <th>接口编码</th>
                  <th>响应状态</th>
                  <th>调用次数</th>
                  <th>调用时间</th>
                  <th>创建时间</th>
                  <th width="95">操作</th>
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
