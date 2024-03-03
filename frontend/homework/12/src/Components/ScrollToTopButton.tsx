export const ScrollToTopButton: React.FC<{
    scrollRef: React.RefObject<HTMLDivElement>;
  }> = ({ scrollRef }) => {
    const scrollToTop = () => {
      if (scrollRef.current) {
        scrollRef.current.scrollIntoView({
          behavior: "smooth",
          block: "start",
        });
      }
    };
  
    return (
      <button
        onClick={scrollToTop}
        style={{ position: "fixed", bottom: "20px", right: "20px" }}
      >
        Scroll To Top
      </button>
    );
  };