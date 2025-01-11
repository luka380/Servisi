export interface AuthInfo {
  token: string;
  username: string;
  user_id: string;
}

export enum KitchenType {
  SERBIAN,
  JAPANESE,
  ITALIAN,
  CHINESE,
  INDIAN,
  FRENCH,
  MEXICAN,
  GREEK,
  THAI,
  SPANISH,
  KOREAN,
  VIETNAMESE,
  GERMAN,
  BRAZILIAN,
  MEDITERRANEAN,
  AMERICAN,
  TURKISH,
  LEBANESE,
  RUSSIAN,
  CARIBBEAN,
  AFRICAN,
  MOROCCAN,
  ARGENTINIAN,
  PERUVIAN,
  SWEDISH,
  POLISH,
  PORTUGUESE,
  MALAYSIAN,
  INDONESIAN,
  EGYPTIAN,
  CUBAN,
  AUSTRALIAN,
  ETHIOPIAN,
  HUNGARIAN,
  PAKISTANI
}

export type Restaurant = {
  id: number;
  name: string;
  address: string;
  phone: string;
  kitchenType: KitchenType
  ownerId: string;
  description: string;
  managerId: string;
}
