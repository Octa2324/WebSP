export interface Account {
  id?: number;
  email: string;
  firstName: string;
  lastName: string;
  password: string;
}

export interface AccountDTO {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
}