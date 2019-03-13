<aside class="main-sidebar">

  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">

    <!-- Sidebar user panel (optional) -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="${webContextPath!}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
      </div>
      <div class="pull-left info">
        <p>
          <#if Session.AUTHORIZE_ACCOUNT_SESSION_KEY ?? >
            ${Session.AUTHORIZE_ACCOUNT_SESSION_KEY.getAccount()!}
          </#if>
        </p>
        <!-- Status -->
        <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
      </div>
    </div>

    <!-- search form (Optional) -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="Search...">
        <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
      </div>
    </form>
    <!-- /.search form -->

    <!-- Sidebar Menu -->
    <ul class="sidebar-menu" data-widget="tree">
      <li class="header">HEADER</li>
      <!-- Optionally, you can add icons to the links -->
      <li <#if (activeMenu ?? && "starter" == activeMenu ) > class="active" </#if> >
        <a href="${webContextPath!}/starter">
          <i class="fa fa-home"></i>
          <span>首页</span>
        </a>
      </li>
      <li <#if (activeMenu ?? && "account" == activeMenu ) > class="active" </#if> >
        <a href="${webContextPath!}/account">
          <i class="fa fa-user"></i>
          <span>帐号管理</span>
        </a>
      </li>
      <!--
      <li class="treeview" <#if (activeMenu ?? && "account" == activeMenu ) > class="active" </#if>>
        <a href="#">
          <i class="fa fa-asterisk"></i>
          <span>基础数据管理</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>
        <ul class="treeview-menu">
          <li>
            <a href="${webContextPath!}/account">
              <i class="fa fa-circle-o"></i>
              帐号管理
            </a>
          </li>
          <li><a href="#">Link in level 2</a></li>
        </ul>
      </li>
      -->
      <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
      <li class="treeview">
        <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
          <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
        </a>
        <ul class="treeview-menu">
          <li><a href="#">Link in level 2</a></li>
          <li><a href="#">Link in level 2</a></li>
        </ul>
      </li>
    </ul>
    <!-- /.sidebar-menu -->
  </section>
  <!-- /.sidebar -->
</aside>