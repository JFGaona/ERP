import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import './Breadcrumb.css';

const Breadcrumb = () => {
    const location = useLocation();
    const pathnames = location.pathname.split('/').filter((x) => x);

    // Mapear nombres de rutas para que sean más amigables
    const pathMap = {
        'admin-dashboard': 'Panel de Administrador',
        'user-dashboard': 'Panel de Usuario',
    };

    return (
        <nav className="breadcrumb">
            <ul className="breadcrumb-list">
                <li className="breadcrumb-item">
                    <Link to="/">Inicio</Link>
                </li>
                {pathnames.map((value, index) => {
                    const to = `/${pathnames.slice(0, index + 1).join('/')}`;
                    const isLast = index === pathnames.length - 1;
                    const displayName = pathMap[value] || value.charAt(0).toUpperCase() + value.slice(1);

                    return (
                        <li key={to} className="breadcrumb-item">
                            {isLast ? (
                                <span>{displayName}</span>
                            ) : (
                                <>
                                    <Link to={to}>{displayName}</Link>
                                    <span className="breadcrumb-separator"> / </span>
                                </>
                            )}
                        </li>
                    );
                })}
            </ul>
        </nav>
    );
};

export default Breadcrumb;