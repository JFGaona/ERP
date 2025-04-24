import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import App from './App';

test('renders login page', () => {
  render(
      <MemoryRouter initialEntries={['/']}>
        <App />
      </MemoryRouter>
  );
  const titleElement = screen.getByText(/Iniciar Sesión/i);
  expect(titleElement).toBeInTheDocument();
});