
      <template #action="{ row }">
        <Space>
          <Button type="primary"
                  size="small"
                  shape="circle"
                  @click="handlerAssignRole(true, row)">
            <Tooltip content="分配权限"
                     transfer>
              <font-awesome-icon :icon="['fas', 'location-arrow']"/>
            </Tooltip>
          </Button>
          <Button type="error"
                  size="small"
                  shape="circle"
                  @click="handlerDeleteUser(true, row)">
            <Tooltip content="删除用户"
                     transfer>
              <font-awesome-icon :icon="['fas', 'trash']"/>
            </Tooltip>
          </Button>
        </Space>
      </template>
