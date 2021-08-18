<template>
  <div class="mb-3 d-flex flex-column">
    <label v-if="label" :for="name" class="form-label" :class="{ 'required-field' : requiredField === 'true'}">{{ label }}</label>
    <select
      v-model="selectedOption"
      @input="event => { $emit('input', event.target.value) }"
      class="form-control"
      >
      <option v-for="(option) in options" :value="option" :key="option">{{ option.name }}</option>
    </select>
  </div>
</template>

<script>

export default {
  props: {
    value: null,
    options: {
      type: Object,
      required: true
    },
    render: {
      type: String,
      required: true
    },
    label: {
      type: String,
      default: "",
    },
  },

  data () {
    return {
      selectedOption: null
    }
  },

  mounted () {
    this.selectedOption = this.value
  },

  watch: {
    value: function (newValue) {
      this.selectedOption = newValue
    }
  }
}

</script>

<style scoped>

label {
  text-align: start;
}

</style>