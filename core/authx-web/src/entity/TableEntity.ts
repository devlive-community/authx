export class TableColumnEntity {
  title: string | undefined
  key: string | undefined
  slot?: string | undefined
  tooltip?: boolean | undefined

  constructor (title: string | undefined, key: string | undefined, slot?: string | undefined, tooltip?: boolean | undefined) {
    this.title = title
    this.key = key
    this.slot = slot
    this.tooltip = tooltip
  }
}
