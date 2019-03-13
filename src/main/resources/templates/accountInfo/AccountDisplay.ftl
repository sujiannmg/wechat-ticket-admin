<!DOCTYPE html>
<html lang="zh">
<#include "../commons-pages/commons-page-head.ftl">
<body>
<div id="wrapper">
  <#include "../commons-pages/commons-nav.ftl">
  <div id="page-wrapper" class="gray-bg dashbard-1">
    <#include "../commons-pages/commons-top-nav.ftl">
    <div class="row wrapper border-bottom white-bg page-heading">
      <div class="col-lg-10">
        <h2>帐号管理</h2>
        <ol class="breadcrumb">
          <li class="active">
            <strong>查看帐号</strong>
          </li>
        </ol>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="ibox float-e-margins">
          <div class="ibox-title">
            <h5>详细帐号信息</h5>
          </div>
          <div class="ibox-content">
            <#if error ?? >
              <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>程序运行异常：</strong> ${error!}
              </div>
            </#if>
            <form id="accountEditForm" method="POST" action="${webContextPath!}/api/base/account/edit" class="form-horizontal dss-base-form">
              <div class="form-group"><label class="col-sm-2 control-label">帐号编号</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.getAccountId()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">帐号</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.getAccount()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-8">
                  <label class="form-control">  <#if account ??> ${account.getName()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">角色</label>
                <div class="col-sm-8">
                  <label class="form-control">
                    <#if (account ?? ) >
                      <#if "admin" = account.getRole() >
                        管理员
                      <#elseif "manager" = account.getRole() >
                        省区经理
                      <#elseif "secretar" = account.getRole() >
                        商务内勤
                      <#else>
                        未知[${account.getRole()!}]
                      </#if>
                    </#if>
                  </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">是否启用</label>
                <div class="col-sm-8">
                  <label class="form-control">
                    <#if (account ?? ) >
                      <#if "y" = account.getEnable() >
                        启用
                      <#else>
                        未启用
                      </#if>
                    </#if>
                  </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">业务版本号</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.getVersion()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">创建用户</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.getCreateUsername()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">创建时间</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.displayCreateTime()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">最后修改用户</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.getModifyUsername()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group"><label class="col-sm-2 control-label">最后修改时间</label>
                <div class="col-sm-8">
                  <label class="form-control"> <#if account ??> ${account.displayModifyTime()!}</#if> </label>
                </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group">
                <div class="col-sm-4 col-sm-offset-2">
                  <a class="btn btn-primary" href="${webContextPath!}/api/base/account/">返回</a>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <#include "../commons-pages/commons-footer.ftl">
  </div>
</div>
<#include "../commons-pages/commons-body-script.ftl">
</body>
</html>