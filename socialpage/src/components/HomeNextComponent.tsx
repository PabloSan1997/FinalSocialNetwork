import { Link, Navigate } from "react-router-dom";




export function HomeNextComponent({pathbase, page, className}:{pathbase:string, page:number, className:string}) {
    const more = () => `${pathbase}${page + 1}`;
    const lesspage = () => `${pathbase}${page - 1}`;
    if (isNaN(page))
        return <Navigate to={`${pathbase}0`} />
    return (
        <nav className={`next_dev ${className}`}>
                {page > 0 ? <Link to={lesspage()}>Back</Link> : null}
                <span>{page}</span>
                <Link to={more()}>Next</Link>
        </nav>
    );
}
