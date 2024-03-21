<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="导航名称" prop="navName">
        <el-input
          v-model="queryParams.navName"
          placeholder="请输入导航名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="navStatus">
        <el-select v-model="queryParams.navStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['action:navbar:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['action:navbar:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['action:navbar:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['action:navbar:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="navbarList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="导航id" align="center" prop="navId" v-if="true"/>
      <el-table-column label="导航名称" align="center" prop="navName" />
      <el-table-column label="导航图标" align="center" prop="navIcon" />
      <el-table-column label="连接地址" align="center" prop="navUrl" />
      <el-table-column label="缩略图" align="center" prop="thumbnail" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.thumbnail" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="navSort" />
      <el-table-column label="状态" align="center" prop="navStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.navStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['action:navbar:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['action:navbar:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改导航信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="导航名称" prop="navName">
          <el-input v-model="form.navName" placeholder="请输入导航名称" />
        </el-form-item>
        <el-form-item label="导航图标" prop="navIcon">
          <el-input v-model="form.navIcon" placeholder="请输入导航图标" />
        </el-form-item>
        <el-form-item label="连接地址" prop="navUrl">
          <el-input v-model="form.navUrl" placeholder="请输入连接地址" />
        </el-form-item>
        <el-form-item label="缩略图" prop="thumbnail">
          <image-upload v-model="form.thumbnail"/>
        </el-form-item>
        <el-form-item label="排序" prop="navSort">
          <el-input v-model="form.navSort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态" prop="navStatus">
          <el-select v-model="form.navStatus" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNavbar, getNavbar, delNavbar, addNavbar, updateNavbar } from "@/api/action/navbar";

export default {
  name: "Navbar",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 导航信息表格数据
      navbarList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        navName: undefined,
        navStatus: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        navName: [
          { required: true, message: "导航名称不能为空", trigger: "blur" }
        ],
        navUrl: [
          { required: true, message: "连接地址不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询导航信息列表 */
    getList() {
      this.loading = true;
      listNavbar(this.queryParams).then(response => {
        this.navbarList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        navId: undefined,
        navName: undefined,
        navIcon: undefined,
        navUrl: undefined,
        thumbnail: undefined,
        navSort: undefined,
        navStatus: undefined,
        updateBy: undefined,
        updateTime: undefined,
        createBy: undefined,
        createTime: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.navId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加导航信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const navId = row.navId || this.ids
      getNavbar(navId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改导航信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.navId != null) {
            updateNavbar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addNavbar(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const navIds = row.navId || this.ids;
      this.$modal.confirm('是否确认删除导航信息编号为"' + navIds + '"的数据项？').then(() => {
        this.loading = true;
        return delNavbar(navIds);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('action/navbar/export', {
        ...this.queryParams
      }, `navbar_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
