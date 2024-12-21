export interface Account {
  id?: number;
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  roles?: string[];
}

export interface AccountDTO {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
}