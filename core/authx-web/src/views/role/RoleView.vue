<template>
  <div>
    <Card dis-hover>
      <template #title>
        路由管理
      </template>
      <template #extra>
        <Tooltip content="添加路由">
          <Button type="primary"
                  shape="circle"
                  size="small"
                  @click="handlerDetailRole(true, null)">
            <font-awesome-icon :icon="['fas', 'plus']"/>
          </Button>
        </Tooltip>
      </template>
      <Table :loading="loading"
             :columns="headers"
             :data="data?.content">
        <template #active="{ row }">
          <Switch v-model="row.active"
                  disabled>
          </Switch>
        </template>
        <template #action="{ row }">
          <Space>
            <Button type="primary"
                    size="small"
                    shape="circle"
                    @click="handlerDetailRole(true, row)">
              <Tooltip content="修改路由"
                       transfer>
                <font-awesome-icon :icon="['fas', 'edit']"/>
              </Tooltip>
            </Button>
          </Space>
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
    </Card>
    <RoleDetail v-if="detailRole.visible"
                :is-visible="detailRole.visible"
                :info="detailRole.info"
                @close="handlerDetailRole(false, null)">
    </RoleDetail>
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import { PageEntity, PageResponseEntity } from '@/entity/PageEntity'
import RoleUtils from '@/views/role/RoleUtils'
import RoleService from '@/services/RoleService'
import RoleDetail from '@/views/role/components/RoleDetail.vue'
import { RoleEntity } from '@/entity/RoleEntity'

export default defineComponent({
  name: 'RoleView',
  components: { RoleDetail },
  created () {
    this.page = new PageEntity()
    this.handlerInitialize()
  },
  data () {
    return {
      page: null as unknown as PageEntity,
      loading: false,
      headers: RoleUtils.headers,
      data: null as unknown as PageResponseEntity,
      detailRole: {
        visible: false,
        info: null as unknown as RoleEntity
      }
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
    },
    handlerDetailRole (value: boolean, info?: RoleEntity) {
      this.detailRole.info = info as RoleEntity
      this.detailRole.visible = value
      if (value === false) {
        this.handlerInitialize()
      }
    }
  }
})
</script>
