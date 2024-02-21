export type QuoteType = {
  _id: string;
  content: string;
  author: string;
  tags: string[];
  authorSlug: string;
  length: number;
  dateAdded: string;
  dateModified: string;
  isVisible: boolean;
};

export type QuoteListPropType = {
  quotes: QuoteType[];
  onClick: React.MouseEventHandler<HTMLButtonElement> | undefined;
};

export type QuotePropType = {
  quote: QuoteType;
  onClick: React.MouseEventHandler<HTMLButtonElement> | undefined;
};

export type AddQuoteButtonProps = {
  onClick: () => Promise<void>;
  loading: boolean;
};

export type FiltersSectionProps = {
  filters: string[];
  onRemoveFilter: (filter: string) => void;
};

export type RemoveFilterButtonProps = {
  filter: string;
  onClick: (filter: string) => void;
};
