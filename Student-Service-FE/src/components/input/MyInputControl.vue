<template>
  <div class="mb-3 d-flex flex-column">
    <label v-if="label" :for="name" class="form-label" :class="{ 'required-field' : requiredField === 'true'}">{{ label }}</label>
    <input
      v-bind="$attrs"
      :type="$attrs.type"
      :id="name"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      :placeholder="label"
      class="form-control"
      @blur="vcomp.$touch()"
    />
    <div v-if="vcomp != null">
    <p
      class="alert alert-danger"
      v-for="(error, index) of vcomp.$errors"
      :key="index"
    >
      <strong>{{ error.$message }}</strong>
    </p>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
    requiredField: {
      type: String,
      default: "",
    },
    vcomp: Object,
    name: String,
  },
};
</script>
<style scoped>
label {
  text-align: start;
}
</style>