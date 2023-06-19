<template>
  <div>
    <Table :loading="loading"
           :columns="headers"
           :data="data?.content">
      <template #permission="{ row }">
        <Tag v-for="role in row.roles"
             v-bind:key="role.name"
             color="primary">
          {{ role.name }}
        </Tag>
      </template>
    </Table>
    <Page v-if="data?.content"
          show-sizer
          show-elevator
          show-total
          style="margin-top: 10px;"
          :modelValue="data.number"
          :total="data.totalElements"
          :page-size="data.size"
          @on-page-size-change="handlerSizeChange"
          @on-change="handlerIndexChange">
    </Page>
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import { PageEntity, PageResponseEntity } from '@/entity/PageEntity'
import UserService from '@/services/admin/UserService'
import UserUtils from '@/views/user/UserUtils'

export default defineComponent({
  name: 'UserView',
  created () {
    this.page = new PageEntity()
    this.handlerInitialize()
  },
  data () {
    return {
      page: null as unknown as PageEntity,
      loading: false,
      headers: UserUtils.headers,
      data: null as unknown as PageResponseEntity
    }
  },
  methods: {
    handlerInitialize () {
      this.loading = true
      UserService.getAllByPage(this.page)
        .then(response => {
          this.data = response.data
          console.log(this.data.content)
        })
        .finally(() => {
          this.loading = false
        })
    },
    handlerSizeChange (size: number) {
      this.page.size = size
      this.handlerInitialize()
    },
    handlerIndexChange (index: number) {
      this.page.page = index
      this.handlerInitialize()
    }
  }
})
</script>
