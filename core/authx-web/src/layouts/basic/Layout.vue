<template>
  <div>
    <Layout>
      <FastDocLayoutHeader>
      </FastDocLayoutHeader>
      <Layout :style="{padding: '0'}">
        <FastDocLayoutContent>
        </FastDocLayoutContent>
      </Layout>
      <FastDocLayoutFooter>
      </FastDocLayoutFooter>
    </Layout>
  </div>
</template>

<script lang="ts">
import FastDocLayoutHeader from '@/layouts/basic/components/LayoutHeader.vue'
import FastDocLayoutContent from '@/layouts/basic/components/LayoutContent.vue'
import FastDocLayoutFooter from '@/layouts/basic/components/LayoutFooter.vue'

export default {
  name: 'FastDocLayoutContainer',
  components: {
    FastDocLayoutHeader,
    FastDocLayoutContent,
    FastDocLayoutFooter
  }
}
</script>
