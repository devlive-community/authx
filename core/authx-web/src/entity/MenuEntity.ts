export class MenuEntity {
  checked: boolean | undefined
  code: string | undefined
  id: number | undefined
  name: string | undefined
  isNew: boolean | undefined
  selected: boolean | undefined
  sorted: number | undefined
  tips: string | undefined
  url: string | undefined
  children: Array<MenuEntity> | undefined
}
