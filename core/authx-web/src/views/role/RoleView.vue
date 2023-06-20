<template>
  <div>
    <Table :loading="loading"
           :columns="headers"
           :data="data?.content">
      <template #avatar="{ row }">
        <Avatar v-if="row.avatar"
                :src="row.avatar">
        </Avatar>
        <Avatar v-else>
          {{ row.name }}
        </Avatar>
      </template>
      <template #permission="{ row }">
        <Tooltip v-for="role in row.roles"
                 transfer
                 v-bind:key="role.name"
                 :content="role.description">
          <Tag color="primary">
            {{ role.name }}
          </Tag>
        </Tooltip>
      </template>
      <template #active="{ row }">
        <Switch v-model="row.active"
                disabled>
        </Switch>
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
import { UserEntity } from '@/entity/UserEntity'
import RoleUtils from '@/views/role/RoleUtils'
import RoleService from '@/services/RoleService'

export default defineComponent({
  name: 'RoleView',
  created () {
    this.page = new PageEntity()
    this.handlerInitialize()
  },
  data () {
    return {
      page: null as unknown as PageEntity,
      loading: false,
      headers: RoleUtils.headers,
      data: null as unknown as PageResponseEntity
    }
  },
  methods: {
    handlerInitialize () {
      this.loading = true
      RoleService.getAllByPage(this.page)
        .then(response => {
          this.data = response.data
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
