<template>
  <div>
    <Menu theme="light" width="auto">
      <div v-for="menu in menus"
           v-bind:key="menu.id">
        <Submenu v-if="menu.children"
                 :name="menu.code">
          <template #title>
            {{ menu.name }}
          </template>
          <MenuItem v-for="children in menu.children"
                    v-bind:key="children.id"
                    :name="children.code"
                    :to="children.url">
            {{ children.name }}
          </MenuItem>
        </Submenu>
        <MenuItem v-else
                  :name="menu.code"
                  :to="menu.url">
          {{ menu.name }}
        </MenuItem>
      </div>
    </Menu>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { MenuEntity } from '@/entity/MenuEntity'

export default defineComponent({
  name: 'FastDocLayoutAside',
  props: {
    // @ts-ignore
    menus: {
      type: Array<MenuEntity>(),
      default: []
    }
  }
})
</script>
