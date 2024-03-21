<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="幻灯名称" prop="bannerName">
        <el-input
          v-model="queryParams.bannerName"
          placeholder="请输入幻灯名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['action:banner:add']"
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
          v-hasPermi="['action:banner:edit']"
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
          v-hasPermi="['action:banner:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['action:banner:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bannerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="幻灯id" align="center" prop="bannerId" v-if="true"/>
      <el-table-column label="幻灯名称" align="center" prop="bannerName" />
      <el-table-column label="连接地址" align="center" prop="bannerUrl" />
      <el-table-column label="缩略图" align="center" prop="thumbnail" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.thumbnail" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="bannerSort" />
      <el-table-column label="类型" align="center" prop="bannerType" />
      <el-table-column label="状态" align="center" prop="bannerStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_show_hide" :value="scope.row.bannerStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['action:banner:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['action:banner:remove']"
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

    <!-- 添加或修改轮播图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="幻灯名称" prop="bannerName">
          <el-input v-model="form.bannerName" placeholder="请输入幻灯名称" />
        </el-form-item>
        <el-form-item label="连接地址" prop="bannerUrl">
          <el-input v-model="form.bannerUrl" placeholder="请输入连接地址" />
        </el-form-item>
        <el-form-item label="缩略图" prop="thumbnail">
          <image-upload v-model="form.thumbnail"/>
        </el-form-item>
        <el-form-item label="排序" prop="bannerSort">
          <el-input v-model="form.bannerSort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态" prop="bannerStatus">
          <el-radio-group v-model="form.bannerStatus">
            <el-radio
              v-for="dict in dict.type.sys_show_hide"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listBanner, getBanner, delBanner, addBanner, updateBanner } from "@/api/action/banner";

export default {
  name: "Banner",
  dicts: ['sys_show_hide'],
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
      // 轮播图表格数据
      bannerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bannerName: undefined,
        bannerType: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bannerId: [
          { required: true, message: "幻灯id不能为空", trigger: "blur" }
        ],
        bannerName: [
          { required: true, message: "幻灯名称不能为空", trigger: "blur" }
        ],
        bannerUrl: [
          { required: true, message: "连接地址不能为空", trigger: "blur" }
        ],
        bannerSort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询轮播图列表 */
    getList() {
      this.loading = true;
      listBanner(this.queryParams).then(response => {
        this.bannerList = response.rows;
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
        bannerId: undefined,
        bannerName: undefined,
        bannerUrl: undefined,
        thumbnail: undefined,
        bannerSort: undefined,
        bannerType: undefined,
        bannerStatus: undefined,
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
      this.ids = selection.map(item => item.bannerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加轮播图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const bannerId = row.bannerId || this.ids
      getBanner(bannerId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改轮播图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.bannerId != null) {
            updateBanner(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addBanner(this.form).then(response => {
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
      const bannerIds = row.bannerId || this.ids;
      this.$modal.confirm('是否确认删除轮播图编号为"' + bannerIds + '"的数据项？').then(() => {
        this.loading = true;
        return delBanner(bannerIds);
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
      this.download('action/banner/export', {
        ...this.queryParams
      }, `banner_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
