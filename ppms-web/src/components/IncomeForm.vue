<template>
  <div>
    <a-modal
      title="收入录入"
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
                {
                  rules: [{ required: true, message: '请输入标题' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="金额">
            <a-input-number
              :formatter="
                (value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
              "
              :parser="(value) => value.replace(/\$\s?|(,*)/g, '')"
              v-decorator="[
                'price',
                {
                  rules: [{ required: true, message: '请输入金额' }],
                  initialValue: '0',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="收入来源">
            <a-input
              v-decorator="[
                'source',
                {
                  rules: [{ required: true, message: '请输入收入来源' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="录入者">
            <a-input
              v-decorator="[
                'recorder',
                {
                  rules: [{ required: true, message: '请输入录入者名字' }],
                  initialValue: '',
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
                  initialValue: '',
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
  name: "IncomeForm",
  data() {
    return {
      confirmLoading: false,
      visible: false,
      form: this.$form.createForm(this, { name: "recorder" }),
    };
  },
  methods: {
    handleOk() {
      this.form.validateFields((err, values) => {
        if (!err) {
          values["date"] = new Date();
          this.$emit("datas", values);

          this.confirmLoading = true;
          setTimeout(() => {
            this.visible = false;
            this.confirmLoading = false;
            this.form.resetFields();
          }, 2000);
        }
      });
    },
    handleCancel() {
      this.visible = false;
      this.form.resetFields();
    },
    setVisible(visible) {
      this.visible = visible;
    },
  },
};
</script>
  
  <style scoped>
</style>
  