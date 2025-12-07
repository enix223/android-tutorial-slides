import React from "react";

type Props = {
  leading?: React.ReactNode;
  trailing?: React.ReactNode;
};

export default function Navbar(props: Props) {
  return (
    <div className="flexw-full p-4 px-6 bg-white drop-shadow-sm mb-2 flex flex-row justify-between text-xl">
      {props.leading}
      <div />
      {props.trailing}
    </div>
  );
}
