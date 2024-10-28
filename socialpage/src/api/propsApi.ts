

export const propsApi = {
    baseUrl: import.meta.env.DEV ? 'http://localhost:3005/api' : `${window.location.origin}/api`,
    onlyAuth: (token: string) => (
        {
            'Authorization': `Bearer ${token}`
        }
    ),
    onlyJson: () => (
        {
            'Content-Type': 'application/json'
        }
    ),
    jsonAuth: (token: string) => (
        {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    )
}