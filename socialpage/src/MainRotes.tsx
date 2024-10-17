import { HashRouter, Navigate, useRoutes } from "react-router-dom";
import { LoginRegister } from "./lyouts/LoginRegister";
import { useAppSelector } from "./store/hooks";
import { Home } from "./lyouts/Home";
import { Header } from "./components/Header";
import { Perfil } from "./lyouts/Perfil";
import { OneImage } from "./lyouts/OneImage";
import { PerfilFriend } from "./lyouts/PerfilFriend";
import { FollowList } from "./lyouts/FollowList";


const MainRedirect = () => {
    const token = useAppSelector(state => state.authReducer.token);
    const routes = token.trim() ? '/home' : '/login';
    return (<Navigate to={routes} />);
}
const RedirectToken = ({ children }: Children) => {
    const token = useAppSelector(state => state.authReducer.token);
    if (!token.trim())
        return <Navigate to='/login' />
    return (
        <>
            {children}
        </>
    );
}

const PageRoutes = () => useRoutes([
    {
        element: <LoginRegister />,
        path: '/login'
    },
    {
        path: '/',
        element: <MainRedirect />
    },
    {
        path: '/home',
        element: (
            <RedirectToken>
                <Navigate to={'/home/0'}/>
            </RedirectToken>
        )
    },
    {
        path:'/home/:page',
        element:(
            <RedirectToken>
                <Home />
            </RedirectToken>
        )
    },
    {
        path:'/perfil',
        element:(
            <RedirectToken>
                <Perfil />
            </RedirectToken>
        )
    },
    {
        path:'/perfilFriend',
        element:(
            <RedirectToken>
                <PerfilFriend/>
            </RedirectToken>
        )
    },
    {
        path:'/followings',
        element:(
            <RedirectToken>
                <FollowList isFollowing={true}/>
            </RedirectToken>
        )
    },
    {
        path:'/followers',
        element:(
            <RedirectToken>
                <FollowList isFollowing={false}/>
            </RedirectToken>
        )
    },
    {
        path:'/oneImage',
        element:(
            <RedirectToken>
                <OneImage/>
            </RedirectToken>
        )
    }
]);


export function MainRoutes() {
    return (
        <HashRouter>
            <Header/>
            <PageRoutes />
        </HashRouter>
    );
}