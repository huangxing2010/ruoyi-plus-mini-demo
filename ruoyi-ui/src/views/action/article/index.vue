<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章标题" prop="articleTitle">
        <el-input
          v-model="queryParams.articleTitle"
          placeholder="请输入文章标题"
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
          v-hasPermi="['action:article:add']"
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
          v-hasPermi="['action:article:edit']"
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
          v-hasPermi="['action:article:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['action:article:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文章标题" align="left" prop="articleTitle" />

      <el-table-column label="分类名称" align="center" width="100" prop="acCategory.categoryName" />

      <el-table-column label="来源" align="center" width="120" prop="source" />
      <el-table-column label="缩略图" align="center" prop="thumbnail" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.thumbnail" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="访问量" align="center" width="120" prop="artHits" />
      <el-table-column label="类型" align="center" width="100" prop="artType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_type" :value="scope.row.artType"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="artStatus" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_status" :value="scope.row.artStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" width="100" />
      <el-table-column label="创建日期" align="center" prop="createTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['action:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['action:article:remove']"
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

    <!-- 添加或修改文章信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="60%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-form-item label="文章标题" prop="articleTitle">
            <el-input v-model="form.articleTitle" placeholder="请输入文章标题" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="文章分类" prop="categoryId">
            <treeselect v-model="form.categoryId" :options="categoryList" :normalizer="normalizer" placeholder="选择分类" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="副标题" prop="subhead">
            <el-input v-model="form.subhead" placeholder="请输入副标题" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来源" prop="source">
            <el-input v-model="form.source" placeholder="请输入来源" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="类型" prop="artType">
            <el-select v-model="form.artType" placeholder="请选择类型">
              <el-option
                v-for="dict in dict.type.sys_article_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="描述" prop="description">
            <el-input v-model="form.description" placeholder="请输入描述" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="缩略图" prop="thumbnail">
            <image-upload v-model="form.thumbnail"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容">
            <editor v-model="form.content" :min-height="192"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="访问量" prop="artHits">
            <el-input v-model="form.artHits == undefined ? 0 : form.artHits" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态" prop="artStatus">
            <el-radio-group v-model="form.artStatus">
              <el-radio
                v-for="dict in dict.type.sys_notice_status"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
          </el-form-item>
        </el-col>

      </el-row>













      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/action/article";
import {getCategory, listCategory} from "@/api/action/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Article",
  dicts: ['sys_notice_status', 'sys_article_type'],
  components: { Treeselect },
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
      // 文章信息表格数据
      articleList: [],
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryId: undefined,
        articleTitle: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        articleId: [
          { required: true, message: "文章id不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "分类id不能为空", trigger: "blur" }
        ],
        articleTitle: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        source: [
          { required: true, message: "来源不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文章信息列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
        console.log(4444111, response)


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
        articleId: undefined,
        categoryId: undefined,
        articleTitle: undefined,
        subhead: undefined,
        source: undefined,
        description: undefined,
        thumbnail: undefined,
        artHits: undefined,
        content: undefined,
        artType: undefined,
        artStatus: undefined,
        remark: undefined,
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
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文章信息";
      listCategory().then(response => {
        this.categoryList = this.handleTree(response.data, "categoryId", "parentId");
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const articleId = row.articleId || this.ids
      listCategory().then(response => {
        this.categoryList = this.handleTree(response.data, "categoryId", "parentId");
      })
      getArticle(articleId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改文章信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.articleId != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addArticle(this.form).then(response => {
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
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否确认删除文章信息编号为"' + articleIds + '"的数据项？').then(() => {
        this.loading = true;
        return delArticle(articleIds);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.categoryId,
        label: node.categoryName,
        children: node.children
      };
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('action/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
