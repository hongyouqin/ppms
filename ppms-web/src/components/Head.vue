<template>
  <div class="root">
    <div class="panel-logo">
      <h4 class="title">{{ logo }}<strong class="text-blue">PPMS</strong></h4>
    </div>
  </div>
</template>
<script>
export default {
  name: "Head",
  props: {
    logo: String,
  },
  data() {
    return {};
  },
};
</script>
<style>
.text-blue {
  font-size: 23px;
  color: #4a81d4;
}

.panel-logo {
  text-align: left;
  padding-top: 10px;
  padding-left: 122px;
  letter-spacing: 3px;
}
</style>