export interface IProject1 {
    id: number
    title: string
    description: string
    users: []
}

export interface IProject {
    id: number
    title: string
    description: string
    children: string[]
}