<template>
  <div>
    <Content :style="{minHeight: '280px', background: '#fff'}">
      <router-view></router-view>
    </Content>
  </div>
</template>

<script lang="ts">
export default {
  name: 'FastDocLayoutContent'
}
</script>
