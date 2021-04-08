<template>
  <div>
    <a-modal
      title="支出录入"
      :visible="visible"
      :confirm-loading="confirmLoading"
      okText="录入"
      cancelText="取消"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <div>
        <a-form
          :form="form"
          :label-col="{ span: 5 }"
          :wrapper-col="{ span: 12 }"
        >
          <a-form-item label="标题">
            <a-input
              v-decorator="[
                'title',
                { rules: [{ required: true, message: '请输入标题' }] },
              ]"
            />
          </a-form-item>
          <a-form-item label="金额">
            <a-input-number
              :default-value="0"
              :formatter="
                (value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
              "
              :parser="(value) => value.replace(/\$\s?|(,*)/g, '')"
              v-decorator="[
                'price',
                {
                  rules: [{ required: true, message: '请输入金额' }],
                  initialValue: 0,
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="支出类型">
            <a-input
              v-decorator="[
                'expensesType',
                {
                  rules: [{ required: true, message: '请输入支出类型' }],
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="冲动性消费">
            <a-select
              v-decorator="[
                'impulse',
                {
                  rules: [
                    { required: true, message: 'Please select your gender!' },
                  ],
                },
              ]"
            >
              <a-select-option value="male"> 是 </a-select-option>
              <a-select-option value="female"> 否 </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="记录者">
            <a-input
              v-decorator="[
                'recorder',
                {
                  rules: [{ required: true, message: '请输入录入者名字' }],
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="备注">
            <a-textarea
              :rows="4"
              v-decorator="[
                'remarks',
                {
                  rules: [{ required: true, message: '请输入备注内容' }],
                },
              ]"
            />
          </a-form-item>
        </a-form>
      </div>
    </a-modal>
  </div>
</template>

<script>
export default {
  name: "ExpenseForm",
  props: {
    // visible: {
    //   type: Boolean,
    //   default: false,
    // },
  },
  data() {
    return {
      confirmLoading: false,
      visible: false,
      form: this.$form.createForm(this, { name: "recorder" }),
    };
  },
  methods: {
    handleOk() {
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel() {
      this.visible = false;
    },
    setVisible(visible) {
      this.visible = visible;
    },
  },
};
</script>

<style scoped>
</style>
