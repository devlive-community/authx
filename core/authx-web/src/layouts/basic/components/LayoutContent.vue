<template>
  <div>
    <Content :style="{background: '#fff'}">
      <router-view></router-view>
    </Content>
  </div>
</template>

<script lang="ts">
export default {
  name: 'FastDocLayoutContent'
}
</script>
