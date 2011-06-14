/*
 * Copyright 2011 David Pearson. GPL v3.
 */

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.*;

/**
 * The actual gameplay view, and associated AI methods.
 *
 * @author David
 */
    public class Panel extends JPanel {
      public Graphics graph;
      public BufferedImage image;
   
      public int[][] board; 
      public boolean isHuman;
      public boolean gameWon;

		public int level;
   
      /**
       * Default constructor for the view.
       */
       public Panel() {
         image =  new BufferedImage(400, 425, BufferedImage.TYPE_INT_RGB);
         graph = image.getGraphics();
         graph.setColor(Color.WHITE);
         graph.fillRect(0, 0, 400, 425);
         graph.setColor(Color.RED);
         graph.fillRect(120, 20, 20, 390);
         graph.fillRect(260, 20, 20, 390);
         graph.fillRect(20, 120, 365, 20);
         graph.fillRect(20, 260, 365, 20);
         board=new int[3][3];
         for (int i=0; i<board.length; i++) {
            for (int j=0; j<3; j++) {
               board[i][j]=0;
            }
         }
         addMouseListener(new Mouse());
         isHuman=true;
         gameWon=false;

			level=-1;

			while (level<0 || level>2) {
				level=Integer.parseInt(JOptionPane.showInputDialog("Enter difficulty from 0-2:"
				));
			}
      }
      
      /**
       * Paints the view.
       * @param  g  the Graphics instance to paint to.
       */
      @Override
      public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
      }
   
      /**
       * Plays a piece at the coordinates for either a human player (X) or the computer (O).
       *
       * @param  x  The x coordinate to play the piece at. 0<x<2.
       * @param  y  The y coordinate to play the piece at. 0<y<2.
       * @param  human  Whether or not the piece is an X.
       *
       * @return Success.
       */
      public boolean playAt(int x, int y, boolean human) {
		 	//System.out.println("Val: "+board[2][2]);
		 	if (x<0 || x>2 || y<0 || y>2) {
				return false;
			}

         if (human) {
			 	if (checkThreeInARow()) {
					return false;
				}
            int actX=0;
            int actY=0;
         
            switch(x){
               case 0:
                  actX=30;
                  break;
               case 1:
                  actX=150;
                  break;
               case 2:
                  actX=290;
                  break;
               default:
                  break;
            }
         
            switch(y){
               case 0:
                  actY=30;
                  break;
               case 1:
                  actY=150;
                  break;
               case 2:
                  actY=290;
                  break;
               default:
                  break;
            }

				if (board[y][x]==0) {
	            ImageIcon ico=new ImageIcon("x.png");
 	            graph.drawImage(ico.getImage(), actX, actY, 85, 85, null);
  	            repaint();

					board[y][x]=1;
				} else {
					//System.out.println("X: "+x+" Y: "+y);
					return false;
				}
         } 
         else {
            int actX=0;
            int actY=0;
         
            switch(x){
               case 0:
                  actX=30;
                  break;
               case 1:
                  actX=150;
                  break;
               case 2:
                  actX=290;
                  break;
               default:
                  break;
            }
         
            switch(y){
               case 0:
                  actY=30;
                  break;
               case 1:
                  actY=150;
                  break;
               case 2:
                  actY=290;
                  break;
               default:
                  break;
            }
         
            ImageIcon ico=new ImageIcon("o.png");
            graph.drawImage(ico.getImage(), actX, actY, 85, 85, null);
            repaint();
         }

			checkThreeInARow();

			return true;
      }

       /**
        * Draws all of the computer's moves at once. Convience method for playAt(x,y,false) looped through the board.
        */
       public void drawRobotPlays() {
            for (int i=0; i<board.length; i++) {
            	for (int j=0; j<board.length; j++) {
         			if (board[i][j]==2) {
							//System.out.println("X: "+j+" Y: "+i);
         				playAt(j, i, false);
         			}
         		}
         	}
      }


      /**
       * Checks for any threes-in-a-row.
       *
       * @return true if there is a three-in-a-row.
       */
      boolean checkThreeInARow() {
			boolean retVal=false;

			if (board[0][0]==board[1][0]&& board[1][0]==board[2][0] && board[0][0]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(70+i, 20, 70+i, 410);
				}
				retVal=true;
			}
			if (board[0][1]==board[1][1]&& board[1][1]==board[2][1] && board[0][1]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(195+i, 20, 195+i, 410);
				}
				retVal=true;
			}
			if (board[0][2]==board[1][2]&& board[1][2]==board[2][2] && board[0][2]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(330+i, 20, 330+i, 410);
				}
				retVal=true;
			}
			if (board[0][0]==board[0][1]&& board[0][1]==board[0][2] && board[0][0]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(20, 70+i, 390, 70+i);
				}
				retVal=true;
			}
			if (board[1][0]==board[1][1]&& board[1][1]==board[1][2] && board[1][0]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(20, 195+i, 390, 195+i);
				}
				retVal=true;
			}
			if (board[2][0]==board[2][1]&& board[2][1]==board[2][2] && board[2][0]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(20, 330+i, 390, 330+i);
				}
				retVal=true;
			}
			if (board[0][0]==board[1][1]&& board[1][1]==board[2][2] && board[0][0]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(20+i, 20, 390+i, 390);
				}
				retVal=true;
			}
			if (board[0][2]==board[1][1]&& board[1][1]==board[2][0] && board[0][2]!=0) {
				graph.setColor(Color.WHITE.darker());
				for (int i=0; i<7; i++) {
					graph.drawLine(370+i, 20, 20+i, 390);
				}
				retVal=true;
			}

			return retVal;
		}
   
        /**
         * Counts the number of two-in-a-rows for the given piece.
         *
         * @param  piece The piece (0 or 1) to count for.
         *
         * @return  The number of two-in-a-rows.
         */
        int countTwoInARows(int piece) {
         int count=0;
			if (level<1 && Math.random()<0.5) {
				return 0;
			}
         if (board[0][0]==board[0][1] && board[0][0]==piece && board[0][2]==0) {
            count+=1;
         } 
         else if (board[0][1]==board[0][2] && board[0][1]==piece && board[0][0]==0) {
            count+=1;
         } 
         else if (board[1][0]==board[1][1] && board[1][0]==piece && board[1][2]==0) {
            count+=1;
         } 
         else if (board[1][1]==board[1][2] && board[1][1]==piece && board[1][0]==0) {
            count+=1;
         } 
         else if (board[2][0]==board[2][1] && board[2][0]==piece && board[2][2]==0) {
            count+=1;
         } 
         else if (board[2][1]==board[2][2] && board[2][1]==piece && board[2][0]==0) {
            count+=1;
         } 
         else if (board[0][0]==board[1][0] && board[0][0]==piece && board[2][0]==0) {
            count+=1;
         } 
         else if (board[1][0]==board[2][0] && board[1][0]==piece && board[0][0]==0) {
            count+=1;
         } 
         else if (board[0][1]==board[1][1] && board[0][1]==piece && board[2][1]==0) {
            count+=1;
         } 
         else if (board[1][1]==board[2][1] && board[1][1]==piece && board[0][1]==0) {
            count+=1;
         } 
         else if (board[0][2]==board[1][2] && board[0][2]==piece && board[2][2]==0) {
            count+=1;
         } 
         else if (board[1][2]==board[2][2] && board[1][2]==piece && board[0][2]==0) {
            count+=1;
         } 
         else if (board[0][0]==board[1][1] && board[0][0]==piece && board[2][2]==0) {
            count+=1;
         } 
         else if (board[1][1]==board[2][2] && board[1][1]==piece && board[0][0]==0) {
            count+=1;
         } 
         else if (board[0][2]==board[1][1] && board[0][2]==piece && board[2][0]==0) {
            count+=1;
         } 
         else if (board[1][1]==board[2][0] && board[1][1]==piece && board[0][2]==0) {
            count+=1;
         }
         return count;
      }

       /**
        * Returns a winning move.
        *
        * @param  piece  the piece (0 or 1) to check for
        *
        * @return the square to play at to block/win.
        */
       int[] checkCanWin(int piece) {
         if (board[0][0]==board[0][1] && board[0][0]==piece && board[0][2]==0) {
            return new int[] {0,2};
         } 
         else if (board[0][1]==board[0][2] && board[0][1]==piece && board[0][0]==0) {
            return new int[] {0,0};
         } 
         else if (board[1][0]==board[1][1] && board[1][0]==piece && board[1][2]==0) {
            return new int[] {1,2};
         } 
         else if (board[1][1]==board[1][2] && board[1][1]==piece && board[1][0]==0) {
            return new int[] {1,0};
         } 
         else if (board[2][0]==board[2][1] && board[2][0]==piece && board[2][2]==0) {
            return new int[] {2,2};
         } 
         else if (board[2][1]==board[2][2] && board[2][1]==piece && board[2][0]==0) {
            return new int[] {2,0};
         } 
         else if (board[0][0]==board[1][0] && board[0][0]==piece && board[2][0]==0) {
            return new int[] {2,0};
         } 
         else if (board[1][0]==board[2][0] && board[1][0]==piece && board[0][0]==0) {
            return new int[] {0,0};
         } 
         else if (board[0][1]==board[1][1] && board[0][1]==piece && board[2][1]==0) {
            return new int[] {2,1};
         } 
         else if (board[1][1]==board[2][1] && board[1][1]==piece && board[0][1]==0) {
            return new int[] {0,1};
         } 
         else if (board[0][2]==board[1][2] && board[0][2]==piece && board[2][2]==0) {
            return new int[] {2,2};
         } 
         else if (board[1][2]==board[2][2] && board[1][2]==piece && board[0][2]==0) {
            return new int[] {0,2};
         } 
         else if (board[0][0]==board[1][1] && board[0][0]==piece && board[2][2]==0) {
            return new int[] {2,2};
         } 
         else if (board[1][1]==board[2][2] && board[1][1]==piece && board[0][0]==0) {
            return new int[] {0,0};
         } 
         else if (board[0][2]==board[1][1] && board[0][2]==piece && board[2][0]==0) {
            return new int[] {2,0};
         } 
         else if (board[1][1]==board[2][0] && board[1][1]==piece && board[0][2]==0) {
            return new int[] {0,2};
         } 
         else if (board[0][0]==board[0][2] && board[0][0]==piece && board[0][1]==0) {
            return new int[] {0,1};
         } 
         else if (board[1][0]==board[1][2] && board[1][0]==piece && board[1][1]==0) {
            return new int[] {1,1};
         } 
         else if (board[2][0]==board[2][2] && board[2][0]==piece && board[2][1]==0) {
            return new int[] {2,1};
         } 
         else if (board[0][0]==board[2][0] && board[0][0]==piece && board[1][0]==0) {
            return new int[] {1,0};
         } 
         else if (board[0][1]==board[2][1] && board[0][1]==piece && board[1][1]==0) {
            return new int[] {1,1};
         } 
         else if (board[0][2]==board[2][2] && board[0][2]==piece && board[1][2]==0) {
            return new int[] {1,2};
         } 
         else if (board[0][0]==board[2][2] && board[0][0]==piece && board[1][1]==0) {
            return new int[] {1,1};
         } 
         else if (board[0][2]==board[2][0] && board[0][2]==piece && board[1][1]==0) {
            return new int[] {1,1};
         } else if (board[0][0]==board[1][1] && board[0][0]==piece && board[2][2]==0) {
            return new int[] {2,2};
         } else {
				//System.out.println("Spot: "+board[2][2]);
			}
         return null;
      }

       /**
        * Plays in the centre square if possible.
        *
        * @param  piece  the piece to play
        * @return played in centre
        */
       boolean playCenterIfPossible(int piece) {
		 	if (level==0) {
				return false;
			}

         if (board[1][1]==0) {
            board[1][1]=piece;
            return true;
         }
         return false;
      }

       /**
        * Plays in the opposite corner if possible.
        *
        * @param  piece  the piece to play
        *
        * @return played in opposite corner
        */
       boolean playOppositeCornerIfPossible(int piece) {
         int oppPiece=1;
         if (piece==1) {
            oppPiece=2;
         }
         if (board[0][0]==oppPiece && board[2][2]==0) {
            board[2][2]=piece;
            return true;
         } 
         else if (board[2][2]==oppPiece && board[0][0]==0) {
            board[0][0]=piece;
            return true;
         } 
         else if (board[0][2]==oppPiece && board[2][0]==0) {
            board[2][0]=piece;
            return true;
         } 
         else if (board[2][0]==oppPiece && board[0][2]==0) {
            board[0][2]=piece;
            return true;
         }
         return false;
      }

        /**
         * Plays in a corner if possible.
         *
         * @param  piece  the piece to play
         *
         * @return played in a corner
         */
        boolean playCornerIfPossible(int piece) {
         if (board[2][2]==0) {
            board[2][2]=piece;
            return true;
         } 
         else if (board[0][0]==0) {
            board[0][0]=piece;
            return true;
         } 
         else if (board[2][0]==0) {
            board[2][0]=piece;
            return true;
         } 
         else if (board[0][2]==0) {
            board[0][2]=piece;
            return true;
         }
         return false;
      }

        /**
         * Plays on an edge if possible.
         *
         * @param  piece  the piece to play
         *
         * @return played on edge
         */
        boolean playEdgeIfPossible(int piece) {
         if (board[0][1]==0) {
            board[0][1]=piece;
            return true;
         } 
         else if (board[1][0]==0) {
            board[1][0]=piece;
            return true;
         } 
         else if (board[1][2]==0) {
            board[1][2]=piece;
            return true;
         } 
         else if (board[2][1]==0) {
            board[2][1]=piece;
            return true;
         }
         return false;
      }
   
       /**
		  * Creates a fork to win if possible.
		  *
		  * @param  piece  the piece to create a fork for.
		  *
		  * @return Success
		  */
		 boolean forkIfPossible(int piece) {
         for (int i=0; i<board.length; i++) {
            for (int j=0; j<3; j++) {
               if (board[i][j]==0) {
                  board[i][j]=piece;
                  if (countTwoInARows(piece)>1) {
                     return true;
                  }
                  board[i][j]=0;
               }
            }
         }
      
         return false;
      }

       /**
		  * Blocks an opponents fork if possible.
		  *
		  * @param  piece  the piece to use to block.
		  *
		  * @return Success
		  */
		 boolean blockForkIfPossible(int piece) {
		 	if (level<1) {
				return false;
			}

		 	int oppPiece=1;
			if (oppPiece==piece) {
				piece=2;
			}
         for (int i=0; i<board.length; i++) {
            for (int j=0; j<3; j++) {
               if (board[i][j]==0) {
                  board[i][j]=oppPiece;
                  if (countTwoInARows(oppPiece)>1) {
	                  board[i][j]=piece;
                     return true;
                  }
                  board[i][j]=0;
               }
            }
         }
      
         return false;
      }

		/**
		 * Blocks a double fork if possible.
		 *
		 * @param  piece  the piece to use to block.
		 *
		 * @return Success
		 */
		boolean blockDoubleForkIfPossible(int piece) {
			if (level<2) {
				return false;
			}
		 	int oppPiece=1;
			if (oppPiece==piece) {
				piece=2;
			}

			if (board[0][0]==board[2][2] && board[0][0]==oppPiece && (board[0][1]==0 || board[1][0]==0 || board[1][2]==0 || board[2][1]==0)) {
				if (board[0][1]==0) {
					board[0][1]=piece;
				} else if(board[1][0]==0) {
					board[1][0]=piece;
				} else if(board[1][2]==0) {
					board[1][2]=piece;
				} else if(board[2][1]==0) {
					board[2][1]=piece;
				}
				return true;
			}

			if (board[0][2]==board[2][0] && board[0][2]==oppPiece && (board[0][1]==0 || board[1][0]==0 || board[1][2]==0 || board[2][1]==0)) {
				if (board[0][1]==0) {
					board[0][1]=piece;
				} else if(board[1][0]==0) {
					board[1][0]=piece;
				} else if(board[1][2]==0) {
					board[1][2]=piece;
				} else if(board[2][1]==0) {
					board[2][1]=piece;
				}
				return true;
			}

			if (board[0][1]==board[1][0] && board[0][1]==oppPiece && board[0][0]==0) {
				board[0][0]=piece;
				return true;
			}

			if (board[0][1]==board[1][2] && board[0][1]==oppPiece && board[0][2]==0) {
				board[0][2]=piece;
				return true;
			}

			if (board[1][2]==board[2][1] && board[1][2]==oppPiece && board[2][2]==0) {
				board[2][2]=piece;
				return true;
			}

			if (board[2][1]==board[1][2] && board[2][1]==oppPiece && board[2][0]==0) {
				board[2][0]=piece;
				return true;
			}

			return false;
		}

		/**
		 * Plays a turn ahead to block a potential setup of of a double fork.
		 *
		 * @param  piece  the piece to use to block.
		 *
		 * @return Success
		 */
		boolean blockDoubleForkInAdvanceIfPossible(int piece) {
			if (level<2) {
				return false;
			}
		 	int oppPiece=1;
			if (oppPiece==piece) {
				piece=2;
			}
         for (int i=0; i<board.length; i++) {
            for (int j=0; j<3; j++) {
               if (board[i][j]==0) {
                  board[i][j]=oppPiece;
                  if (blockDoubleForkIfPossible(piece)) {
	                  board[i][j]=0;
                     return true;
                  }
                  board[i][j]=0;
               }
            }
         }
      
         return false;
		}

		/**
		 * Counts the number of pieces that have been played by both players.
		 *
		 * @return the number of pieces
		 */
		int countPieces() {
			int count=0;
			for(int i=0; i<board.length; i++) {
				for (int j=0; j<board.length; j++) {
					if (board[i][j]!=0) {
						count+=1;
					}
				}
			}

			return count;
		}
   
       /**
		  * Has the AI play defensively for the given piece.
		  *
		  * @param  piece  the piece to play for.
		  */
		 void play(int piece) {
         int opp=1;
         if (piece==1) {
            opp=2;
         }
			//System.out.println(""+countPieces());
			if (countPieces()==1 && playCenterIfPossible(piece)) {
				System.out.println("Initial");
			} else if (checkCanWin(piece)!=null) {
            board[checkCanWin(piece)[0]][checkCanWin(piece)[1]]=2;
            gameWon=true;
         } 
         else if (checkCanWin(opp)!=null) {
            board[checkCanWin(opp)[0]][checkCanWin(opp)[1]]=2;
				//System.out.println("Blocked");
         }
			else if (blockDoubleForkInAdvanceIfPossible(piece) && level>1) {
         }
			else if (blockDoubleForkIfPossible(piece) && level>1) {
				System.out.println("Blocked");
         }/*
			else if (blockForkIfPossible(piece)) {
				System.out.println("Blocked");
         }*/
         else if (forkIfPossible(piece)) {
         } 
         else if (playCenterIfPossible(piece)) {
         } 
         else if (playOppositeCornerIfPossible(piece)) {
         } 
         else if (playCornerIfPossible(piece)) {
         } 
         else if (playEdgeIfPossible(piece)) {
         } 
         else {
            gameWon=true;
         }
      }
   
   
   
   
       /**
		  * Responsible for handling mouse events on the panel.
		  */
		 private class Mouse extends MouseAdapter {
          /**
			  * Responds to a pressed button and plays at the appropriate place.
			  *
			  * @param  e  the fird MouseEvent
			  */
			 public void mousePressed(MouseEvent e) {
            //System.out.println(e.getY());
            int x=-1;
            int y=-1;
            if (e.getX()>=0 && e.getX()<=112) {
               x=0;
            } 
            else if (e.getX()>=133 && e.getX()<=249) {
               x=1;
            } 
            else if (e.getX()>=271) {
               x=2;
            }
         
            if (e.getY()>=0 && e.getY()<=112) {
               y=0;
            } 
            else if (e.getY()>=133 && e.getY()<=249) {
               y=1;
            } 
            else if (e.getY()>=271) {
               y=2;
            }

				//System.out.println("Play at X: "+x+" Y:"+y);
         
            if (playAt(x, y, isHuman) && x!=-1 && y!=-1 && !checkThreeInARow() && countPieces()!=9) {
	         	play(2);
 		        	drawRobotPlays();
				}
         }
      }
   
   }